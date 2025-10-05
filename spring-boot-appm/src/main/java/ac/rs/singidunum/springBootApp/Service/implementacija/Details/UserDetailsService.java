package ac.rs.singidunum.springBootApp.Service.implementacija.Details;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermission;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Security.CustomUserDetails;
import ac.rs.singidunum.springBootApp.Service.implementacija.Student.RegistrovanKorisnikService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    RegistrovanKorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<RegistrovaniKorisnik> korisnik = korisnikService.findKorisnikByKorisnickoIme(username);

        if (korisnik.isPresent()) {
            // Kreiramo listu autoriteta (dozvola) za korisnika
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            
            for (UserPermission userPermission : korisnik.get().getPravoPristupa()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(userPermission.getPermission().getIme()));
            }

            // Vraćamo instancu tvoje klase CustomUserDetails sa svim potrebnim informacijama
            return new CustomUserDetails(
                korisnik.get().getId(),                    // ID korisnika
                korisnik.get().getKorisnickoIme(),         // Korisničko ime
                korisnik.get().getLozinka(),               // Lozinka
                grantedAuthorities                         // Dozvole
            );
        } else {
            throw new UsernameNotFoundException("Korisnik nije pronađen pod imenom: " + username + " !");
        }
    }
}
