package ch.bytecrowd.voci.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.bytecrowd.voci.interfaces.Keyable;

@Entity
public class Sprache implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String sprache;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSprache() {
		return sprache;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sprache == null) ? 0 : sprache.hashCode());
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
		Sprache other = (Sprache) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sprache == null) {
			if (other.sprache != null)
				return false;
		} else if (!sprache.equals(other.sprache))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sprache [id=" + id + ", sprache=" + sprache + "]";
	}
}
