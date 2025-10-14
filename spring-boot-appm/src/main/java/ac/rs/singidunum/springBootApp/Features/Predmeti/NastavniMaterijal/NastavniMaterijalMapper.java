package ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal.NastavniMaterijalDTO.NastavniMaterijalDTORecord;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO.SluzbenikStudentskeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class NastavniMaterijalMapper implements Mapper<NastavniMaterijalDTORecord, NastavniMaterijal> {

	@Override
	public NastavniMaterijalDTORecord map(NastavniMaterijal e) {
		if(e == null) {
			return null;
		}
		
		SluzbenikStudentskeDTORecord podnosilacZahteva = null;
		if(e.getPodnosilacZahteva() != null) {
			podnosilacZahteva = new SluzbenikStudentskeDTORecord(
					e.getPodnosilacZahteva().getId(),
					e.getPodnosilacZahteva().getJmbg(),
					e.getPodnosilacZahteva().getTelefon(),
					e.getPodnosilacZahteva().getNalogAktivan(),
					new RegistrovaniKorisnikDTORecord(
							e.getPodnosilacZahteva().getKorisnik().getId(),
							e.getPodnosilacZahteva().getKorisnik().getIme(),
							e.getPodnosilacZahteva().getKorisnik().getKorisnickoIme(),
							null,
							e.getPodnosilacZahteva().getKorisnik().getEmail(),
							null, 
							null,
							null));

		}
		NastavnikDTORecord autorizator = null;
		if(e.getAutorizator() != null) {
			autorizator = new NastavnikDTORecord(
					e.getAutorizator().getId(),
					e.getAutorizator().getBiografija(),
					e.getAutorizator().getJmbg(),
					e.getAutorizator().getTelefon(),
					e.getAutorizator().getPoslovniMail(),
					e.getAutorizator().getBrojSlobodnihDana(),
					e.getAutorizator().getBrojIskoristenihDana(), null, null, null
					);

		}
		NastavniMaterijalDTORecord nDto = 
				new NastavniMaterijalDTORecord(e.getId(), e.getNaziv(),e.getOdobreno(),e.getKolicina(), autorizator,podnosilacZahteva );
		
	
		return nDto;
	}



}
