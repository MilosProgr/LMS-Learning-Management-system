package ac.rs.singidunum.springBootApp.Features.Mesto;




import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO.DrzavaDTORecord;

public class MestoDTO {
	
	
	public record MestoDTORecord(
	Long id,
	String naziv,
	DrzavaDTORecord drzava
	) {}
	
	
	
	
	
}
