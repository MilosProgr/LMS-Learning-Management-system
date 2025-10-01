package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Zvanje;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TipZvanja implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @OneToMany(mappedBy = "tipZvanja")
    private Set<Zvanje> zvanja;

    public TipZvanja() {
    }

    public TipZvanja(Long id, String naziv, Set<Zvanje> zvanja) {
        this.id = id;
        this.naziv = naziv;
        this.zvanja = zvanja;
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

    public Set<Zvanje> getZvanja() {
        return zvanja;
    }

    public void setZvanja(Set<Zvanje> zvanja) {
        this.zvanja = zvanja;
    }
}
