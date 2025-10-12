package ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave;

import jakarta.annotation.PostConstruct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO.TipNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;

import java.util.List;

@Service
public class TipNastaveService extends GenericCrudService<TipNastaveDTORecord, TipNastave, Long> {

	private static final List<String> DEFAULT_TIPOVI_NASTAVE = List.of(
			"Predavanja",
			"Vežbe",
			"Laboratorijske vežbe",
			"Seminari",
			"Konsultacije",
			"Projekti"
	);

	protected TipNastaveService(CrudRepository<TipNastave, Long> repository, Mapper<TipNastaveDTORecord, TipNastave> mapper) {
		super(repository, mapper);
	}

	@PostConstruct
	public void initDefaultTipovi() {
		for (String naziv : DEFAULT_TIPOVI_NASTAVE) {
			boolean exists = getAll().stream()
					.anyMatch(dto -> dto.naziv().equalsIgnoreCase(naziv));
			if (!exists) {
				TipNastave entity = new TipNastave();
				entity.setNaziv(naziv);
				save(entity);
			}
		}
	}


}
