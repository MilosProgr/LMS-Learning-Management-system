package ac.rs.singidunum.springBootApp.Features.Predmeti.IspitniRok;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class IspitniRokMapper implements Mapper<IspitniRokDTO, IspitniRok> {

	@Override
	public IspitniRokDTO map(IspitniRok e) {
		if(e == null) {
			return null;
		}
		IspitniRokDTO iDto = new IspitniRokDTO(e.getId(), e.getNaziv(), e.getPocetak(), e.getKraj(),e.getRedovan());
		
		if(e.getPrijavljeniIspiti() != null) {
			iDto.setPrijavljeniIspiti(
					e.getPrijavljeniIspiti().stream().map(
							prijavljeniIspiti ->
							new PrijavljeniIspitDTO(prijavljeniIspiti.getId(),
									prijavljeniIspiti.getPrijavljen(),
									prijavljeniIspiti.getBrojPrijava())
							).collect(Collectors.toList())
					);
		}
		return iDto;
	}

	@Override
	public List<IspitniRokDTO> map(List<IspitniRok> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
