package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti;

public class FileDTO {
	private Long id;
	private String opis;
	private String url;
	
	public FileDTO(Long id, String opis, String url) {
		super();
		this.id = id;
		this.opis = opis;
		this.url = url;
	}

	public FileDTO() {
		super();
		// TODO Auto-generated constructor stub
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
