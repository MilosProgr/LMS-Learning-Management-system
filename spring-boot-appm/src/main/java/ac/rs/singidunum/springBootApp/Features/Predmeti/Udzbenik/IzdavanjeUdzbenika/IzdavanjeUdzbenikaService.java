package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika.IzdavanjeUdzbenikaDTO.IzdavanjeUdzbenikaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodini;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniRepository;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class IzdavanjeUdzbenikaService extends GenericCrudService<IzdavanjeUdzbenikaDTORecord, IzdavanjeUdzbenika, Long> {

	private final StudentNaGodiniRepository studentRepository;

	@Autowired
	public IzdavanjeUdzbenikaService(
			CrudRepository<IzdavanjeUdzbenika, Long> repository,
			Mapper<IzdavanjeUdzbenikaDTORecord, IzdavanjeUdzbenika> mapper,
			StudentNaGodiniRepository studentRepository) {
		super(repository, mapper);
		this.studentRepository = studentRepository;
	}

	@Override
	public IzdavanjeUdzbenikaDTORecord save(IzdavanjeUdzbenika entity) {
		if (entity.getPodnosilacZahteva() != null && entity.getPodnosilacZahteva().getId() != null) {
			StudentNaGodini student = studentRepository.findById(entity.getPodnosilacZahteva().getId())
					.orElseThrow(() -> new RuntimeException("Student not found"));
			entity.setPodnosilacZahteva(student);
		}
		return super.save(entity);
	}
}
