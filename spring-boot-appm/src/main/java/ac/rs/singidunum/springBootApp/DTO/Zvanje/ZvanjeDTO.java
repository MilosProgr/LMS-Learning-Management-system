package ac.rs.singidunum.springBootApp.DTO.Zvanje;

import java.util.Date;

public class ZvanjeDTO {
    private Long id;
    private Date datumIzbora;
    private Date datumPrestanka;
    private Long nastavnikId;
    private Long naucnaOblastId;
    private Long tipZvanjaId;

    public ZvanjeDTO() {
    }

    public ZvanjeDTO(Long id, Date datumIzbora, Date datumPrestanka, Long nastavnikId, Long naucnaOblastId, Long tipZvanjaId) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.nastavnikId = nastavnikId;
        this.naucnaOblastId = naucnaOblastId;
        this.tipZvanjaId = tipZvanjaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public Long getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(Long nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public Long getNaucnaOblastId() {
        return naucnaOblastId;
    }

    public void setNaucnaOblastId(Long naucnaOblastId) {
        this.naucnaOblastId = naucnaOblastId;
    }

    public Long getTipZvanjaId() {
        return tipZvanjaId;
    }

    public void setTipZvanjaId(Long tipZvanjaId) {
        this.tipZvanjaId = tipZvanjaId;
    }
}