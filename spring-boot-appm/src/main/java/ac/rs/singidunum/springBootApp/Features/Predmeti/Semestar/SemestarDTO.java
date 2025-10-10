package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SemestarDTO {

	
	public record SemestarDTORecord(
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			Long id,
			@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			LocalDateTime datumPocetka,
			@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			LocalDateTime datumKraja,
			String tip) {}
	
	
	
	
	
	
	
	
}
