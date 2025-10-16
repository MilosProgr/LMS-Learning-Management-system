package ac.rs.singidunum.springBootApp.Features.Drzava;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;

public class DrzavaDTO {
	
	
	public record DrzavaDTORecord(
			 Long id,
			 String naziv,
			
			 Set<MestoDTORecord> mesta
			) {}
	
}
