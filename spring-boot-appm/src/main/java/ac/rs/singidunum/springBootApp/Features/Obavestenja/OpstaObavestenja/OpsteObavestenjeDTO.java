package ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja;

import java.time.LocalDate;
import java.time.LocalTime;

public class OpsteObavestenjeDTO {



	public record OpsteObavestenjeDTORecord(
			 Long id,
			 String naslov,
			 String tekst,
			 LocalDate datum,
			 LocalTime vreme

			) {}
	

}
