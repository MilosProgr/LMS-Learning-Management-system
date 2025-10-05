package ac.rs.singidunum.springBootApp.Features.Obavestenja.File;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

@Component
public class FileMapper implements Mapper<FileDTO, File> {

	@Override
	public FileDTO map(File e) {
		if(e == null) {
			return null;
		}
		FileDTO fDto = 
				new FileDTO(e.getId(),
						e.getOpis(), 
						e.getUrl());
		return fDto;
	}

	@Override
	public List<FileDTO> map(List<File> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
