package ch.bytecrowd.voci.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.bytecrowd.voci.interfaces.Keyable;
import ch.bytecrowd.voci.util.HashUtil;

@Entity
public class Benutzer implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique=true)
	private String username;

	@Column
	private String passwort;

	public Benutzer(String username, String passwort) {
		super();
		this.username = username;
		this.setPasswort(passwort);
	}
	
	public Benutzer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = HashUtil.hashMD5(passwort);
	}
	
	public void setHaschedPasswort(String passwort) {
		this.passwort = passwort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((passwort == null) ? 0 : passwort.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benutzer other = (Benutzer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (passwort == null) {
			if (other.passwort != null)
				return false;
		} else if (!passwort.equals(other.passwort))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Benutzer [id=" + id + ", username=" + username + ", passwort=******]";
	}
}
