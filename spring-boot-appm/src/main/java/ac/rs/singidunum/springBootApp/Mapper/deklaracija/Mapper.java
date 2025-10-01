package ac.rs.singidunum.springBootApp.Mapper.deklaracija;

import java.util.ArrayList;
import java.util.List;

public interface Mapper<T,E> {
	
	T map(E e);
	
	//Na ovaj nacin ne moramo klasu koja implementira mapper da rucno pisemo da nam se pretvori u DTO list,
	//to nam interface sam radi kada ga implementiramo,zato se zove default :)
	default List<T> map(List<E> e){
		List<T> output = new ArrayList<T>();
    	
    	if(e != null) {
    		//tradicionalni pristup
    		for(E entity: e) {
    			T mapiranEntitet = map(entity);
    			output.add(mapiranEntitet);
    		}
    		//trenutni pristup
    		//output = e.stream().map(this::map).collect(Collectors.toList());
    	}
    	return output;
	}
}
