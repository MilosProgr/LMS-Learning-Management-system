package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import java.util.Set;

public class TipZvanjaDTO {
   
    
    public record TipZvanjaDTORecord(
    		  Long id,
    		  String naziv,
    		  Set<Long> zvanjaIds
    		) {}
}