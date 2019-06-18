package ch.bytecrowd.voci.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ch.bytecrowd.voci.interfaces.Keyable;

@Entity
public class LernSession implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Benutzer benutzer;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<LernSessionUebersetzungUebersetzung> lernPool;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public Set<LernSessionUebersetzungUebersetzung> getLernPool() {
		return lernPool;
	}

	public void setLernPool(Set<LernSessionUebersetzungUebersetzung> lernPool) {
		this.lernPool = lernPool;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benutzer == null) ? 0 : benutzer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lernPool == null) ? 0 : lernPool.hashCode());
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
		LernSession other = (LernSession) obj;
		if (benutzer == null) {
			if (other.benutzer != null)
				return false;
		} else if (!benutzer.equals(other.benutzer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lernPool == null) {
			if (other.lernPool != null)
				return false;
		} else if (!lernPool.equals(other.lernPool))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LernSession [id=" + id + ", Benutzer=" + benutzer + ", lernPool=" + lernPool + "]";
	}

}
