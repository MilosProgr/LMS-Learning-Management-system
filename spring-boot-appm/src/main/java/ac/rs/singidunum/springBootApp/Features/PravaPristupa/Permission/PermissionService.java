package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class PermissionService extends GenericCrudService<PermissionDTORecord, Permission, Long> {
	private PermissionRepository pRepos;
	protected PermissionService(CrudRepository<Permission, Long> repository, Mapper<PermissionDTORecord, Permission> mapper) {
		super(repository, mapper);
		this.pRepos = (PermissionRepository) repository;
	}
	
	public Optional<Permission> findPermissionByIme(String ime){
		return pRepos.findPermissionByIme(ime);
	}

}
