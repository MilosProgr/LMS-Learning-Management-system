package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Zvanje;

import java.util.Set;

public class NaucnaOblastDTO {
    private Long id;
    private String naziv;
    private Set<Long> nastavniciIds;

    public NaucnaOblastDTO() {
    }

    public NaucnaOblastDTO(Long id, String naziv, Set<Long> nastavniciIds) {
        this.id = id;
        this.naziv = naziv;
        this.nastavniciIds = nastavniciIds;
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

    public Set<Long> getNastavniciIds() {
        return nastavniciIds;
    }

    public void setNastavniciIds(Set<Long> nastavniciIds) {
        this.nastavniciIds = nastavniciIds;
    }
}
