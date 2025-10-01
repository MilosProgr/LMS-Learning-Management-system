package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Log.Log;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Log.LogRepository;



@Service
public class LogService {
	@Autowired
	LogRepository lr;
	
	public void save(Log l) {
		lr.save(l);
	}
}
