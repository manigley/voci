package ch.bytecrowd.voci.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ch.bytecrowd.voci.interfaces.Keyable;

@Entity
public class UebersetzungUebersetzung implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade=CascadeType.MERGE)
	private Uebersetzung muttersprache = new Uebersetzung();

	@ManyToOne(cascade=CascadeType.MERGE)
	private Kapitel kapitel;

	@ManyToOne(cascade=CascadeType.MERGE)
	private Uebersetzung fremdsprache = new Uebersetzung();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Uebersetzung getMuttersprache() {
		return muttersprache;
	}

	public void setMuttersprache(Uebersetzung muttersprache) {
		this.muttersprache = muttersprache;
	}

	public Kapitel getKapitel() {
		return kapitel;
	}

	public void setKapitel(Kapitel kapitel) {
		this.kapitel = kapitel;
	}

	public Uebersetzung getFremdsprache() {
		return fremdsprache;
	}

	public void setFremdsprache(Uebersetzung fremdsprache) {
		this.fremdsprache = fremdsprache;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fremdsprache == null) ? 0 : fremdsprache.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kapitel == null) ? 0 : kapitel.hashCode());
		result = prime * result + ((muttersprache == null) ? 0 : muttersprache.hashCode());
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
		UebersetzungUebersetzung other = (UebersetzungUebersetzung) obj;
		if (fremdsprache == null) {
			if (other.fremdsprache != null)
				return false;
		} else if (!fremdsprache.equals(other.fremdsprache))
			return false;
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
		if (muttersprache == null) {
			if (other.muttersprache != null)
				return false;
		} else if (!muttersprache.equals(other.muttersprache))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UebersetzungUebersetzung [id=" + id + ", muttersprache=" + muttersprache + ", kapitel=" + kapitel
				+ ", fremdsprache=" + fremdsprache + "]";
	}
}
