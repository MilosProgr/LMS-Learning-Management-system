package ac.rs.singidunum.springBootApp.Features.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LogService {
	@Autowired
	LogRepository lr;
	
	public void save(Log l) {
		lr.save(l);
	}
}
