package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.ObavestenjaAktivnosti;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.FileDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.File;

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
