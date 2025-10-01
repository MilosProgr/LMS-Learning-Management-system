package ac.rs.singidunum.springBootApp.Model.PravaPristupa;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Permission implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Long id;
	
	@OneToMany(mappedBy = "permission",orphanRemoval = true)
	private Set<UserPermission> pravaPristupa = new HashSet<>();
	
	@Column(name = "ime",nullable = false)
	private String ime;

	public Permission(Long id, String ime) {
		super();
		this.id = id;
		this.ime = ime;
	}

	public Permission(String ime) {
		super();
		this.ime = ime;
	}

	public Permission() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<UserPermission> getPravaPristupa() {
		return pravaPristupa;
	}

	public void setPravaPristupa(Set<UserPermission> pravaPristupa) {
		this.pravaPristupa = pravaPristupa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	
	
	
}
