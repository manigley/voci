package ch.bytecrowd.voci.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ch.bytecrowd.voci.interfaces.Keyable;

@Entity
public class Uebersetzung implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String text;

	@ManyToOne
	private Sprache sprache;


	public Uebersetzung(String text, Sprache sprache) {
		super();
		this.text = text;
		this.sprache = sprache;
	}
	
	public Uebersetzung() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Sprache getSprache() {
		return sprache;
	}

	public void setSprache(Sprache sprache) {
		this.sprache = sprache;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sprache == null) ? 0 : sprache.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Uebersetzung other = (Uebersetzung) obj;
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
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Uebersetzung [id=" + id + ", text=" + text + ", land=" + sprache + "]";
	}

}
