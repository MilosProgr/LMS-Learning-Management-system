package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Udzbenici;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Udzbenici.IzdavanjeUdzbenikaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Udzbenici.IzdavanjeUdzbenika;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Student.StudentNaGodiniRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class IzdavanjeUdzbenikaService extends GenericCrudService<IzdavanjeUdzbenikaDTO, IzdavanjeUdzbenika, Long> {

	private final StudentNaGodiniRepository studentRepository;

	@Autowired
	public IzdavanjeUdzbenikaService(
			CrudRepository<IzdavanjeUdzbenika, Long> repository,
			Mapper<IzdavanjeUdzbenikaDTO, IzdavanjeUdzbenika> mapper,
			StudentNaGodiniRepository studentRepository) {
		super(repository, mapper);
		this.studentRepository = studentRepository;
	}

	@Override
	public IzdavanjeUdzbenikaDTO save(IzdavanjeUdzbenika entity) {
		if (entity.getPodnosilacZahteva() != null && entity.getPodnosilacZahteva().getId() != null) {
			StudentNaGodini student = studentRepository.findById(entity.getPodnosilacZahteva().getId())
					.orElseThrow(() -> new RuntimeException("Student not found"));
			entity.setPodnosilacZahteva(student);
		}
		return super.save(entity);
	}
}
