package ch.bytecrowd.voci.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ch.bytecrowd.voci.interfaces.Keyable;

@Entity
public class LernSessionUebersetzungUebersetzung implements Serializable, Keyable {

	private static final long serialVersionUID = -1398161877534987569L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private UebersetzungUebersetzung uebersetzungUebersetzung;

	private LernSession lernSession;

	@Column
	private Boolean gelernt = Boolean.FALSE;

	@Column
	private Boolean fehler = Boolean.FALSE;

	public LernSessionUebersetzungUebersetzung() {
		super();
	}
	
	public LernSessionUebersetzungUebersetzung(UebersetzungUebersetzung uebersetzungUebersetzung) {
		super();
		this.uebersetzungUebersetzung = uebersetzungUebersetzung;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UebersetzungUebersetzung getUebersetzungUebersetzung() {
		return uebersetzungUebersetzung;
	}

	public void setUebersetzungUebersetzung(UebersetzungUebersetzung uebersetzungUebersetzung) {
		this.uebersetzungUebersetzung = uebersetzungUebersetzung;
	}

	public LernSession getLernSession() {
		return lernSession;
	}

	public void setLernSession(LernSession lernSession) {
		this.lernSession = lernSession;
	}

	public Boolean getGelernt() {
		return gelernt;
	}

	public void setGelernt(Boolean gelernt) {
		this.gelernt = gelernt;
	}

	public Boolean getFehler() {
		return fehler;
	}

	public void setFehler(Boolean fehler) {
		this.fehler = fehler;
	}
}