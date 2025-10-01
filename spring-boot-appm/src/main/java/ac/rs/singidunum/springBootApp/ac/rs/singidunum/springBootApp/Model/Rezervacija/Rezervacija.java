package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Rezervacija;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Nastavnici.Nastavnik;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "rezervacije")
public class Rezervacija implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String opis;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datumOd;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datumDo;

    @ManyToOne
    @JoinColumn(name = "nastavnik_id", nullable = false)
    private Nastavnik nastavnik;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOdmora statusOdmora;

    public Rezervacija(Long id, String opis, LocalDate datumOd, LocalDate datumDo, StatusOdmora statusOdmora) {
        this.id = id;
        this.opis = opis;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.statusOdmora = statusOdmora;
    }

    public Rezervacija() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public StatusOdmora getStatusOdmora() {
        return statusOdmora;
    }

    public void setStatusOdmora(StatusOdmora statusOdmora) {
        this.statusOdmora = statusOdmora;
    }
}

