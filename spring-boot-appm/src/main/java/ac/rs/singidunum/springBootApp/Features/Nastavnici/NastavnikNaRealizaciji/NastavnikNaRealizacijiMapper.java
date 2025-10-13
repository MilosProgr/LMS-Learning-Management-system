package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

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
				new NastavnikDTORecord(e.getNastavnik().getId(),
						e.getNastavnik().getBiografija(),	
						e.getNastavnik().getJmbg(),
						e.getNastavnik().getTelefon(),
						e.getNastavnik().getPoslovniMail(),
						e.getNastavnik().getBrojSlobodnihDana(),
						e.getNastavnik().getBrojIskoristenihDana(), null, null, null
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

//	@Override
//	public List<NastavnikNaRealizacijiDTO> map(List<NastavnikNaRealizaciji> e) {
//		// TODO Auto-generated method stub
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

}
