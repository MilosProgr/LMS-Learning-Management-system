package ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs.KursDTO.KursDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



//@CrossOrigin(origins = "http://localhost:4200") //resenje za dobijanje dozvola za pristup podacima
@Controller
@RequestMapping("/api/kursevi")
public class KursController extends GenericCrudController<KursDTORecord, Kurs, Long> {
	@Autowired
	private KursService kursService;

	@Override
	protected CrudService<KursDTORecord, Kurs, Long> getService() {
		// TODO Auto-generated method stub
		return kursService;
	}
	
//	@GetMapping
//	public ResponseEntity<List<KursDTO>> getAllKursevi(){
//		return new ResponseEntity<>(kursService.findAll(),HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<KursDTO> getKursId(@PathVariable Long id){
//		KursDTO k = kursService.findOne(id);
//		if(k != null) {
//			return new ResponseEntity<>(k,HttpStatus.OK);
//		} 
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//	
//	@PostMapping("/{id}")
//	public ResponseEntity<KursDTO> createKurs(@RequestBody Kurs kurs)
//	{
//		KursDTO k = kursService.save(kurs);
//		if(k!=null) {
//			return new ResponseEntity<KursDTO>(k,HttpStatus.OK);
//		} else {
//			return new ResponseEntity<KursDTO>(HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<KursDTO> updateKurs (@PathVariable Long id,@RequestBody Kurs kurs){
//		kurs.setId(id);
//		KursDTO k = kursService.save(kurs);
//		if(k!=null) {
//			return new ResponseEntity<>(k,HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<KursDTO> deleteKurs(@PathVariable("id") Long id){
//		if(kursService.delete(id)) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
}
