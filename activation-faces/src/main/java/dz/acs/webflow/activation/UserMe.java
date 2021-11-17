package dz.acs.webflow.activation;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UserMe implements Serializable {
	
	private static final long serialVersionUID = 8735345747125639717L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ident;

	public Long getIdent() {
		return ident;
	}

	public void setIdent(Long ident) {
		this.ident = ident;
	}

	private String username;

	private String password;

	private String name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserMe(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public UserMe() {
		super();
	}	
	
}
