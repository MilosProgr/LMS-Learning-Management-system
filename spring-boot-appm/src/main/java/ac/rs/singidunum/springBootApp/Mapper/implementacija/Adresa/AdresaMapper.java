package ac.rs.singidunum.springBootApp.Mapper.implementacija.Adresa;

import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Adresa;

@Component
public class AdresaMapper implements Mapper<AdresaDTO, Adresa> {

	@Override
	public AdresaDTO map(Adresa e) {
		if(e == null) {
			return null;
		}
		AdresaDTO aDto = 
				new AdresaDTO(e.getId(), e.getUlica(), e.getBroj());
		
		if(e.getMesto() != null) {
			aDto.setMesto(
					new MestoDTO(e.getMesto().getId(), e.getMesto().getNaziv())
					);
		}
		return aDto;
	}

//	@Override
//	public List<AdresaDTO> map(List<Adresa> e) {
//		if(e == null) {
//			return Collections.emptyList();
//		}
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

//	@Override
//	public AdresaDTO map(Adresa e) {
//		AdresaDTO adresaDTO =
//		new AdresaDTO(
//				e.getId(),
//				e.getUlica(),
//				e.getBroj(),
//				e.getMesto() != null ? e.getMesto().getId() : null
//				);
////		adresaDTO.setMesto(
////				new MestoDTO(e.getMesto().getId(), e.getMesto().getNaziv(), new DrzavaDTO(null, e.getMesto().getDrzava().getNaziv()))
////				);
//			return adresaDTO;
//	}
//
//	@Override
//	public List<AdresaDTO> map(List<Adresa> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());
//
//	}


}
