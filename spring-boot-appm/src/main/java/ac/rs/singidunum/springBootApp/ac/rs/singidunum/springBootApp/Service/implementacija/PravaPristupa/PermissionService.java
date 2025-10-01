package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.PravaPristupa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.PravaPristupa.PermissionDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.PravaPristupa.Permission;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.PravaPristupa.PermissionRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class PermissionService extends GenericCrudService<PermissionDTO, Permission, Long> {
	private PermissionRepository pRepos;
	protected PermissionService(CrudRepository<Permission, Long> repository, Mapper<PermissionDTO, Permission> mapper) {
		super(repository, mapper);
		this.pRepos = (PermissionRepository) repository;
	}
	
	public Optional<Permission> findPermissionByIme(String ime){
		return pRepos.findPermissionByIme(ime);
	}

}
