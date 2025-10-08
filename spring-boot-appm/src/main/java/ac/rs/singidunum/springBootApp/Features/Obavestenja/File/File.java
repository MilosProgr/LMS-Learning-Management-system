package ac.rs.singidunum.springBootApp.Features.Obavestenja.File;

import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//seminarski,projekat, elektronski test, praktican zadatak
public class File implements BaseEntity<Long> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String opis;
    private String url;
	public File() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public File(Long id, String opis, String url) {
		super();
		this.id = id;
		this.opis = opis;
		this.url = url;
	}

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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
