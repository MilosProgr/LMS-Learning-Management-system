package ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast;

import jakarta.persistence.*;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;

@Entity
public class NaucnaOblast implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToMany
    @JoinTable(
            name = "nastavnik_naucna_oblast",
            joinColumns = @JoinColumn(name = "naucna_oblast_id"),
            inverseJoinColumns = @JoinColumn(name = "nastavnik_id")
    )
    private Set<Nastavnik> nastavnici;

    public NaucnaOblast() {
    }

    public NaucnaOblast(Long id, String naziv, Set<Nastavnik> nastavnici) {
        this.id = id;
        this.naziv = naziv;
        this.nastavnici = nastavnici;
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

    public Set<Nastavnik> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(Set<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }
}
