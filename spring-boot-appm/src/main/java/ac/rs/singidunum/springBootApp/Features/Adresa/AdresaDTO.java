package ac.rs.singidunum.springBootApp.Features.Adresa;

import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;

public class AdresaDTO {
	
	
	public record AdresaDTORecord(
	 Long id,
	 String ulica,
	 String broj,
	 MestoDTORecord mesto){}
	
	
}
