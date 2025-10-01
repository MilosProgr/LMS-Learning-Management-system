package ac.rs.singidunum.springBootApp.Mapper.implementacija.Nastavnici;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikDTO;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.FileDTO;
import ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.NastavnikNaRealizaciji;

@Component
public class NastavnikNaRealizacijiMapper implements Mapper<NastavnikNaRealizacijiDTO, NastavnikNaRealizaciji> {

	@Override
	public NastavnikNaRealizacijiDTO map(NastavnikNaRealizaciji e) {
		if(e == null) {
			return null;
		}
		NastavnikNaRealizacijiDTO nrDto =
				new NastavnikNaRealizacijiDTO(e.getId(), e.getBrojCasova());
		
		nrDto.setNastavnik(
				new NastavnikDTO(e.getNastavnik().getId(),
						e.getNastavnik().getBiografija(),
						e.getNastavnik().getJmbg(),
						e.getNastavnik().getTelefon(),
						e.getNastavnik().getPoslovniMail(),
						e.getNastavnik().getBrojSlobodnihDana(),
						e.getNastavnik().getBrojIskoristenihDana()
						)

				);
		if(e.getRealizacijaPredmeta() != null) {
			nrDto.setRealizacijaPredmeta(
					e.getRealizacijaPredmeta().stream().map(
							rPredmeti ->
							new RealizacijaPredmetaDTO(
									rPredmeti.getId(),
									new PredmetDTO(rPredmeti.getPredmet().getId(),
											rPredmeti.getPredmet().getNaziv(),
											rPredmeti.getPredmet().getEsbn(),
											rPredmeti.getPredmet().getObavezan(),
											rPredmeti.getPredmet().getBrojPredavanja(),
											rPredmeti.getPredmet().getBrojVezbi(),
											rPredmeti.getPredmet().getDrugiObliciNastave(),
											rPredmeti.getPredmet().getIstrazivackiRad(),
											rPredmeti.getPredmet().getOstaliCasovi()),
									null
									)
							).collect(Collectors.toSet())

			);
		}

		return nrDto;
	}

	@Override
	public List<NastavnikNaRealizacijiDTO> map(List<NastavnikNaRealizaciji> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
