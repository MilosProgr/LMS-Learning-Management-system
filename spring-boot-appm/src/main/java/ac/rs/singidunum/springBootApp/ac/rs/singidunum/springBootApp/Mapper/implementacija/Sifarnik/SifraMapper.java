package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Sifarnik;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Sifarnik.Sifra;


@Component
public class SifraMapper implements Mapper<Map<String, Object>, Sifra> {

	@Override
	public Map<String, Object> map(Sifra e) {
		if(e == null) {
			return null;
		}
		Map<String,Object> sifraMap = new LinkedHashMap<>();
		sifraMap.put("id", e.getId());
		sifraMap.put("tekst", e.getTekst());
		
		return sifraMap;
	}

	@Override
	public List<Map<String, Object>> map(List<Sifra> e) {
		if(e == null) {
			return Collections.emptyList();
		}
		return e.stream().map(this::map).collect(Collectors.toList());
	}
}
