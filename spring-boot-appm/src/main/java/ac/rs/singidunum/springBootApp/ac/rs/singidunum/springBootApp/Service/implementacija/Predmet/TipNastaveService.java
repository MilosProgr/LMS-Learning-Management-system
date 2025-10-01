package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import jakarta.annotation.PostConstruct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TipNastave;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

import java.util.List;

@Service
public class TipNastaveService extends GenericCrudService<TipNastaveDTO, TipNastave, Long> {

	private static final List<String> DEFAULT_TIPOVI_NASTAVE = List.of(
			"Predavanja",
			"Vežbe",
			"Laboratorijske vežbe",
			"Seminari",
			"Konsultacije",
			"Projekti"
	);

	protected TipNastaveService(CrudRepository<TipNastave, Long> repository, Mapper<TipNastaveDTO, TipNastave> mapper) {
		super(repository, mapper);
	}

	@PostConstruct
	public void initDefaultTipovi() {
		for (String naziv : DEFAULT_TIPOVI_NASTAVE) {
			boolean exists = getAll().stream()
					.anyMatch(dto -> dto.getNaziv().equalsIgnoreCase(naziv));
			if (!exists) {
				TipNastave entity = new TipNastave();
				entity.setNaziv(naziv);
				save(entity);
			}
		}
	}


}
