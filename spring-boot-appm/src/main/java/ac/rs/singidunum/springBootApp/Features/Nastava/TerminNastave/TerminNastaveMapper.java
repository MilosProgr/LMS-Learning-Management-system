package ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class TerminNastaveMapper implements Mapper<TerminNastaveDTO, TerminNastave> {

	@Override
	public TerminNastaveDTO map(TerminNastave e) {
		if(e == null) {
			return null;
		}
		TerminNastaveDTO tDto =
				new TerminNastaveDTO(e.getId(),
						e.getVremePocetka(),
						e.getVremePocetka(), null);
		tDto.setTipNasstave(
				new TipNastaveDTO(e.getTipNastave().getId(), e.getTipNastave().getNaziv())
				);
		tDto.setRealizacijaPredmeta(
				new RealizacijaPredmetaDTO(e.getId(),
						new PredmetDTO(e.getRealizacijaPredmeta().getPredmet().getId(), e.getRealizacijaPredmeta().getPredmet().getNaziv(), e.getRealizacijaPredmeta().getPredmet().getEsbn(), e.getRealizacijaPredmeta().getPredmet().getObavezan(), e.getRealizacijaPredmeta().getPredmet().getBrojPredavanja(), e.getRealizacijaPredmeta().getPredmet().getBrojVezbi(), e.getRealizacijaPredmeta().getPredmet().getDrugiObliciNastave(), e.getRealizacijaPredmeta().getPredmet().getIstrazivackiRad(), e.getRealizacijaPredmeta().getPredmet().getOstaliCasovi()),
						new NastavnikNaRealizacijiDTO(e.getRealizacijaPredmeta().getNastavnikNaRealizaciji().getId(), e.getRealizacijaPredmeta().getNastavnikNaRealizaciji().getBrojCasova())
						)
				);
		tDto.setIshod(
				new IshodDTO(e.getIshod().getId(), e.getIshod().getOpis(), e.getIshod().isPolozeno(), null)
				);
		
		return tDto;
	}

	@Override
	public List<TerminNastaveDTO> map(List<TerminNastave> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
