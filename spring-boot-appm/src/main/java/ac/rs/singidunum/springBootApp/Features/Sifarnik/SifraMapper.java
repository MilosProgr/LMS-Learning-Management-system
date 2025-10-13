package ac.rs.singidunum.springBootApp.Features.Sifarnik;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;



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


}
