package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;


import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.Permission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionService;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionService;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;
import ac.rs.singidunum.springBootApp.Security.UserDetailsService;
import ac.rs.singidunum.springBootApp.Security.Utils.TokenUtils;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api")
public class RegistrovaniKorisnikController extends GenericCrudController<RegistrovaniKorisnikDTORecord, RegistrovaniKorisnik, Long> {
	
	@Autowired
	RegistrovanKorisnikService korisnikService;
	
	@Autowired
	UserPermissionService userPermissionService;
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Override
	protected CrudService<RegistrovaniKorisnikDTORecord, RegistrovaniKorisnik, Long> getService() {
		// TODO Auto-generated method stub
		return korisnikService;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_SLUZBA", "ROLE_NASTAVNIK"})
	@RequestMapping(path = "/korisnici",method = RequestMethod.GET)
    public ResponseEntity<List<RegistrovaniKorisnikDTORecord>> findAll() throws IllegalAccessException, InstantiationException {
        return super.getAll();
    }
	
	//endpoint za osnovne podatke registrovanih korisnika
	@RequestMapping(path = "/korisnici/osnovniPodaci", method = RequestMethod.GET)
	public ResponseEntity<List<RegistrovaniKorisnikOsnovniPodaciDTO>> getOsnovniPodaci() {
	    return ResponseEntity.ok(korisnikService.findAllOsnovniPodaci());
	}

	//Login radi posle registracije kad su lozinke hashovane!!
	@RequestMapping(path = "/login", method = RequestMethod.POST) 
    public ResponseEntity<String> login(@RequestBody RegistrovaniKorisnik korisnik) {
       try {
           // provera postojanja korisnika
           Optional<RegistrovaniKorisnik> postojeci = korisnikService.findKorisnikByKorisnickoIme(korisnik.getKorisnickoIme());
           if (postojeci.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Korisnik sa korisničkim imenom '" + korisnik.getKorisnickoIme() + "' ne postoji!"
               );
           }
    	   System.out.println(userDetailsService.loadUserByUsername(korisnik.getKorisnickoIme()));
           UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(korisnik.getKorisnickoIme(),
                   korisnik.getLozinka());
           Authentication auth = authenticationManager.authenticate(token);
           SecurityContextHolder.getContext().setAuthentication(auth);

           String jwt = tokenUtils.generateToken(userDetailsService.loadUserByUsername(korisnik.getKorisnickoIme()));
           System.out.println(jwt);

           return new ResponseEntity<String>(jwt, HttpStatus.OK);
    	   
       } catch (BadCredentialsException e) {
           // ako je lozinka pogresna
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                   "Pogrešna lozinka za korisnika '" + korisnik.getKorisnickoIme() + "'!");
       } catch (ResponseStatusException ex) {
           throw ex;
       } catch (Exception e) {
           // sve ostale greške su 500 Internal Server Error
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                   "Došlo je do greške u sistemu: " + e.getMessage());
       }
		
    }
	
	@PostMapping("/register/{role}")
    @Transactional
    public ResponseEntity<RegistrovaniKorisnikDTORecord> register(@PathVariable("role") String role, @RequestBody RegistrovaniKorisnik korisnik) {
        //List<Permission> permissions = permissionService.findPermissionByName(role);
		// 409 — korisničko ime već postoji
	    Optional<RegistrovaniKorisnik> postojeciKorisnik =
	            korisnikService.findKorisnikByKorisnickoIme(korisnik.getKorisnickoIme());
	    if (postojeciKorisnik.isPresent()) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "Korisnicko ime'" + korisnik.getKorisnickoIme() + "' je vec korisceno!");
	    }
	    
        Optional<Permission> permission = permissionService.findPermissionByIme(role);
        if (!permission.isPresent()) {
        	System.out.println("Nije nadjena permisija za datu rolu: " + role);
        	throw new ResponseStatusException(HttpStatus.CONFLICT, "Nije pronadjena odgovarajuca uloga. Kontaktirajte administratora.");
        }
        
        Optional<RegistrovaniKorisnik> postojeciPoEmailu =
                korisnikService.findKorisnikByEmail(korisnik.getEmail());
        if (postojeciPoEmailu.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email '" + korisnik.getEmail() + "' je već iskorišćen!");
        }
        
        korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
        RegistrovaniKorisnikDTORecord savedKorisnik = korisnikService.save(korisnik);

        
        UserPermissionDTORecord uDto = userPermissionService.save(new UserPermission(permission.get(),korisnik));
        Set<UserPermissionDTORecord> skupPravaPristupa = new HashSet<>();
        skupPravaPristupa.add(uDto);
        
        savedKorisnik = new RegistrovaniKorisnikDTORecord(savedKorisnik.id(),
        		savedKorisnik.ime(),
        		savedKorisnik.prezime(),
        		savedKorisnik.korisnickoIme(),
        		savedKorisnik.lozinka(),
        		savedKorisnik.email(),
        		skupPravaPristupa, null);
//        savedKorisnik.setPravaPristupa(skupPravaPristupa);
        
       
        System.out.println("Korisnik uspesno registrovan! " + savedKorisnik.id());
        return new ResponseEntity<>(savedKorisnik, HttpStatus.CREATED);
        
    }
	
//	 @Secured("ROLE_ADMIN")
	 @RequestMapping(path = "/korisnici/{id}",method = RequestMethod.GET)
	    public ResponseEntity<RegistrovaniKorisnikDTORecord> getById(Long id) {
	        return super.getById(id);
	 }
	
	 @Override
	 @Secured("ROLE_ADMIN")
	 @RequestMapping(path = "/korisnici",method = RequestMethod.POST)
	 public ResponseEntity<RegistrovaniKorisnikDTORecord> create(RegistrovaniKorisnik korisnik) {
		 return super.create(korisnik);
	 }

    @Override
    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/korisnici/{id}",method = RequestMethod.PUT)
    public ResponseEntity<RegistrovaniKorisnikDTORecord> update(@PathVariable("id") Long id,@RequestBody RegistrovaniKorisnik korisnik) {
        korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
    	return super.update(id,korisnik);
    }

    @Override
    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/korisnici/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<RegistrovaniKorisnikDTORecord> delete(Long id) {
        return super.delete(id);
    }

}
