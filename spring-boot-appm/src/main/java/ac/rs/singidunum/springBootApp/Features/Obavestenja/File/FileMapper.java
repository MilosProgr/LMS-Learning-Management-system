package ac.rs.singidunum.springBootApp.Features.Obavestenja.File;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO.FileDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class FileMapper implements Mapper<FileDTORecord, File> {

	@Override
	public FileDTORecord map(File e) {
		if(e == null) {
			return null;
		}
		FileDTORecord fDto = 
				new FileDTORecord(e.getId(),
						e.getOpis(), 
						e.getUrl());
		return fDto;
	}

//	@Override
//	public List<FileDTO> map(List<File> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

}
