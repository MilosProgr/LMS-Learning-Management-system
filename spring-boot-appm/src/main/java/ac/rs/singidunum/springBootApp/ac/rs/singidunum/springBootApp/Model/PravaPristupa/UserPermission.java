package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.PravaPristupa;

import java.io.Serializable;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserPermission implements Serializable,BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "permission_id",nullable = false)
    private Permission permission;
    @ManyToOne(optional = false,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "korisnik_id")
    private RegistrovaniKorisnik korisnik;

    public UserPermission(Permission permission,RegistrovaniKorisnik korisnik) {
//        super();
//        this.id = id;
        this.permission = permission;
        this.korisnik = korisnik;
    }


    public UserPermission() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public RegistrovaniKorisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(RegistrovaniKorisnik korisnik) {
        this.korisnik = korisnik;
    }

}
