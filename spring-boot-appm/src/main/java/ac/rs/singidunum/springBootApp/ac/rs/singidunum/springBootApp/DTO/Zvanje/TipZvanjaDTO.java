package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Zvanje;

import java.util.Set;

public class TipZvanjaDTO {
    private Long id;
    private String naziv;
    private Set<Long> zvanjaIds;

    public TipZvanjaDTO() {
    }

    public TipZvanjaDTO(Long id, String naziv, Set<Long> zvanjaIds) {
        this.id = id;
        this.naziv = naziv;
        this.zvanjaIds = zvanjaIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Long> getZvanjaIds() {
        return zvanjaIds;
    }

    public void setZvanjaIds(Set<Long> zvanjaIds) {
        this.zvanjaIds = zvanjaIds;
    }
}