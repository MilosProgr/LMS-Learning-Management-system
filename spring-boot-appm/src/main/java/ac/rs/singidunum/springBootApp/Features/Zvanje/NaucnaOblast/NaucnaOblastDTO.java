package ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast;

import java.util.Set;

public class NaucnaOblastDTO {
   
    
    public record NaucnaOblastDTORecord(
    		 Long id,
    	     String naziv,
    	     Set<Long> nastavniciIds
    		) {}
}
