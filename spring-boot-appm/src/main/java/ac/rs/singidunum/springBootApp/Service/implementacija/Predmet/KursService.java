package ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.predmet.KursDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet.KursMapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Kurs;
import ac.rs.singidunum.springBootApp.Repository.Predmet.KursRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class KursService extends GenericCrudService<KursDTO, Kurs, Long> {

	protected KursService(CrudRepository<Kurs, Long> repository, Mapper<KursDTO, Kurs> mapper) {
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
