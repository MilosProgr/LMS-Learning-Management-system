package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;


import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.UdzbenikDTO.UdzbenikDTORecord;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO.SluzbenikStudentskeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;

public class IzdavanjeUdzbenikaDTO {
	
	
	public record IzdavanjeUdzbenikaDTORecord(
			 Long id,
			 Integer kolicina,
			 StudentNaGodiniDTORecord podnosilacZahteva,
			 SluzbenikStudentskeDTORecord autorizator,
			 UdzbenikDTORecord udzbenik,
			 Boolean odobreno,
			 Long podnosilacZahtevaId,
			 StudijskiProgramDTORecord studijskiProgram
			) {}
}
