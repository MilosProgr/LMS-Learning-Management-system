package ac.rs.singidunum.springBootApp.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Model.Log.Log;
import ac.rs.singidunum.springBootApp.Service.implementacija.Log.LogService;

import java.time.LocalDateTime;

@Aspect
@Component
public class Logger {
	
	@Autowired
	LogService logService;


   
    
   

    @Before("@annotation(ac.rs.singidunum.springBootApp.Aspect.Logged)")
    public void logPocetakIzvrsavanjaAnotacija(JoinPoint jp) {
        System.out.println("Pre izvrsavanja metode. [Logged]");
        System.out.println(jp.getSignature());
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null) ? authentication.getName() : "Unknown User";
        
        String changeType = jp.getSignature().getName();
        String details = "";

        for (Object o : jp.getArgs()) {
            System.out.println(o);
            details += o.toString() + " ";
        }

        Log logDTO = new Log(null,LocalDateTime.now(), changeType, username, details);
        logService.save(logDTO);
    }
    
    @AfterReturning(pointcut ="execution(* ac.rs.singidunum.springBootApp.Service.*.*(..)) && args(id, ..)", returning="retVal")
	public void logAfter(JoinPoint jp, Long id, Object retVal) {
		System.out.println(jp.toString());
		System.out.println("Logovanje podataka " + id);
		System.out.println(retVal);
	}

}
