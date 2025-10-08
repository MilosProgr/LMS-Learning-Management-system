package ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje;

import jakarta.persistence.*;

import java.util.Date;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblast;
import ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja.TipZvanja;
import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;

@Entity
public class Zvanje implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datumIzbora;
    private Date datumPrestanka;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id", referencedColumnName = "id")
    private Nastavnik nastavnik;

    @ManyToOne
    @JoinColumn(name = "naucna_oblast_id", referencedColumnName = "id")
    private NaucnaOblast naucnaOblast;

    @ManyToOne
    @JoinColumn(name = "tip_zvanja_id", referencedColumnName = "id")
    private TipZvanja tipZvanja;

    public Zvanje() {
    }

    public Zvanje(Long id, Date datumIzbora, Date datumPrestanka, Nastavnik nastavnik, NaucnaOblast naucnaOblast, TipZvanja tipZvanja) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.nastavnik = nastavnik;
        this.naucnaOblast = naucnaOblast;
        this.tipZvanja = tipZvanja;
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

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public NaucnaOblast getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public TipZvanja getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanja tipZvanja) {
        this.tipZvanja = tipZvanja;
    }
}
