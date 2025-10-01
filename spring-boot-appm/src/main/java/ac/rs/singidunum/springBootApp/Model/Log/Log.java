package ac.rs.singidunum.springBootApp.Model.Log;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private LocalDateTime timestamp;
    private String changeType;
    private String username;
    private String details;

    // Constructors, getters, and setters

    public Log() {
    }

    public Log(Long id,LocalDateTime timestamp, String changeType, String username, String details) {
        this.id = id;
    	this.timestamp = timestamp;
        this.changeType = changeType;
        this.username = username;
        this.details = details;
    }
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}