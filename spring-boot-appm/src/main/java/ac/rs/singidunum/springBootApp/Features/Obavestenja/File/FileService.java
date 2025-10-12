package ac.rs.singidunum.springBootApp.Features.Obavestenja.File;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO.FileDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class FileService extends GenericCrudService<FileDTORecord, File, Long> {

	@Autowired
	FileRepository fileRepository;
	
	protected FileService(CrudRepository<File, Long> repository, Mapper<FileDTORecord, File> mapper) {
		super(repository, mapper);
	}
	
	 @Override
	    public FileDTORecord save(File entity) {
	        // Provera jedinstvenosti po opisu
	        Optional<File> byOpis = fileRepository.findByOpis(entity.getOpis());
	        if (byOpis.isPresent()) {
	        	throw new ResponseStatusException(
	                    HttpStatus.CONFLICT, "Već postoji instrument evaluacije sa istim opisom: " + entity.getOpis());
	        }

	        // Provera jedinstvenosti po URL-u
	        Optional<File> byUrl = fileRepository.findByUrl(entity.getUrl());
	        if (byUrl.isPresent()) {
	        	throw new ResponseStatusException(
	                    HttpStatus.CONFLICT, "Već postoji fajl sa istim URL-om: " + entity.getUrl());
	        }

	        return super.save(entity);
	    }

	    @Override
	    public FileDTORecord update(File entity) {
	        Long id = entity.getId();

	        // Provera po opisu (da ne postoji drugi sa istim opisom)
	        Optional<File> byOpis = fileRepository.findByOpis(entity.getOpis());
	        if (byOpis.isPresent() && !byOpis.get().getId().equals(id)) {
	        	throw new ResponseStatusException(
	                    HttpStatus.CONFLICT, "Već postoji instrument evaluacije sa tim opisom: " + entity.getOpis());
	        }

	        // Provera po URL-u (da ne postoji drugi sa istim URL-om)
	        Optional<File> byUrl = fileRepository.findByUrl(entity.getUrl());
	        if (byUrl.isPresent() && !byUrl.get().getId().equals(id)) {
	        	throw new ResponseStatusException(
	                    HttpStatus.CONFLICT, "Već postoji instrument evaluacije sa tim URL-om: " + entity.getUrl());
	        }

	        return super.update(entity);
	    }

}
