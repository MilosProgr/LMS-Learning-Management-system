package ac.rs.singidunum.springBootApp.Features.Rezervacija;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class RezervacijaDTO {
//    private Long id;
//    private String opis;
//    
//    private LocalDate datumOd;
//    private LocalDate datumDo;
//    private Long nastavnikId;
//    private StatusOdmora statusOdmora;
//
//    public RezervacijaDTO(Long id, String opis, LocalDate datumOd, LocalDate datumDo, Long nastavnikId, StatusOdmora statusOdmora) {
//        this.id = id;
//        this.opis = opis;
//        this.datumOd = datumOd;
//        this.datumDo = datumDo;
//        this.nastavnikId = nastavnikId;
//        this.statusOdmora = statusOdmora;
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getOpis() {
//        return opis;
//    }
//
//    public void setOpis(String opis) {
//        this.opis = opis;
//    }
//
//    public LocalDate getDatumOd() {
//        return datumOd;
//    }
//
//    public void setDatumOd(LocalDate datumOd) {
//        this.datumOd = datumOd;
//    }
//
//    public LocalDate getDatumDo() {
//        return datumDo;
//    }
//
//    public void setDatumDo(LocalDate datumDo) {
//        this.datumDo = datumDo;
//    }
//
//    public Long getNastavnikId() {
//        return nastavnikId;
//    }
//
//    public void setNastavnikId(Long nastavnikId) {
//        this.nastavnikId = nastavnikId;
//    }
//
//    public StatusOdmora getStatusOdmora() {
//        return statusOdmora;
//    }
//
//    public void setStatusOdmora(StatusOdmora statusOdmora) {
//        this.statusOdmora = statusOdmora;
//    }
    
    public record RezervacijaDTORecord(
    		 Long id,
    	     String opis,
    	     LocalDate datumOd,
    	     LocalDate datumDo,
    	     Long nastavnikId,
    	     StatusOdmora statusOdmora
    		) {}
    
}
