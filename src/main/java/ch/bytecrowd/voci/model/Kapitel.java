package ch.bytecrowd.voci.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.bytecrowd.voci.interfaces.Keyable;

@Entity
public class Kapitel implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique=true)
	private String kapitel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKapitel() {
		return kapitel;
	}

	public void setKapitel(String kapitel) {
		this.kapitel = kapitel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kapitel == null) ? 0 : kapitel.hashCode());
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
		Kapitel other = (Kapitel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kapitel == null) {
			if (other.kapitel != null)
				return false;
		} else if (!kapitel.equals(other.kapitel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kapitel [id=" + id + ", kapitel=" + kapitel + "]";
	}
}
