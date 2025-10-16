package ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje;

import java.util.Date;

public class ZvanjeDTO {
    
    
    public record ZvanjeDTORecord(
    		  Long id,
    		  Date datumIzbora,
    		  Date datumPrestanka,
    		  Long nastavnikId,
    		  Long naucnaOblastId,
    		  Long tipZvanjaId
    		) {}
}