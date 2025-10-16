package ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs.KursDTO.KursDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class KursService extends GenericCrudService<KursDTORecord, Kurs, Long> {

	protected KursService(CrudRepository<Kurs, Long> repository, Mapper<KursDTORecord, Kurs> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}

	
	
	
//	public List<KursDTO> findAll(){
//		return KursMapper.map((List<Kurs>) kursRepository.findAll());
//	}
//	
//	public KursDTO findOne(Long id) {
//		Kurs kurs = kursRepository.findById(id).orElse(null);
//		
//		if(kurs == null) {
//			return null;
//		}
//		return KursMapper.map(kursRepository.findById(id).orElse(null));
//	}
//	
//	public KursDTO save(Kurs kurs) {
//		return KursMapper.map(kursRepository.save(kurs));
//	}
//	
//	public KursDTO update(Kurs updateKurs) {
//		if(kursRepository.existsById(updateKurs.getId())) {
//			return KursMapper.map(kursRepository.save(updateKurs));
//		}
//		return null;
//	}
//	
//	public boolean delete(Long id) {
//		if(kursRepository.existsById(id)) {
//			
//			kursRepository.deleteById(id);
//			return true;
//		}
//		return false;
//	}
}
