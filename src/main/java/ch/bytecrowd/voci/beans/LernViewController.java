package ch.bytecrowd.voci.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bytecrowd.voci.model.LernSession;
import ch.bytecrowd.voci.model.LernSessionUebersetzungUebersetzung;
import ch.bytecrowd.voci.model.UebersetzungUebersetzung;
import ch.bytecrowd.voci.services.LernSessionUebersetzungUebresezungService;
import ch.bytecrowd.voci.services.UebersetzungUebersetzungService;

@Named("lernViewController")
@SessionScoped
public class LernViewController implements Serializable {

	private static final long serialVersionUID = 7795084631562974462L;

	public enum LernType {
		LERN_ALL("LERN_ALL"),
		LERN_FAILD_AND_NOT_LERNED("LERN_FAILD_AND_NOT_LERNED"),
		LERN_NOT_LERNED("LERN_NOT_LERNED");

		private String text;
		private LernType(String text) {
			this.text = text;
		}
		@Override
		public String toString() {
			return text;
		}
	};
	
	//@Inject
	//private LernSessionController lernSessionController;
	@Inject
	private LernSessionUebersetzungUebresezungService lernSessionUebersetzungUebresetunzgService;
	@Inject
	private UebersetzungUebersetzungService uebersetzungUebersetzungService;
	
	private LernSession curentLernSession = null;
	private Integer uebersetungCounter = 0;
	
	private Iterator<LernSessionUebersetzungUebersetzung> toLernIterator = null;
	private LernSessionUebersetzungUebersetzung curentLesnSessionUebersetzung = null;
	private LernType selectedLernType = LernType.LERN_ALL;
	private String uebersetzung = new String();
	private Set<LernSessionUebersetzungUebersetzung> toLern = new HashSet<>();
		
	public void initLernData() {
		if (selectedLernType != null) {
			if (selectedLernType.equals(LernType.LERN_ALL)) {
				initLernDataLERN_ALL();
			} else if (selectedLernType.equals(LernType.LERN_FAILD_AND_NOT_LERNED)) {
				initLernDataLERN_FAILD_AND_NOT_LERNED();
			}else if (selectedLernType.equals(LernType.LERN_NOT_LERNED)) {
				initLernDataLERN_NOT_LERNED();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "kein Lerntyp ausgewählt", ""));
		}
		toLernIterator = toLern.iterator();
		uebersetungCounter = 0;
		nextLernSessionUebersetzung();
	}

	public void initLernDataLERN_NOT_LERNED() {
		toLern = new HashSet<>();
		for (LernSessionUebersetzungUebersetzung lsuu : curentLernSession.getLernPool()) {
			if (lsuu != null && !lsuu.getGelernt())
				toLern.add(lsuu);
		}
	}

	private boolean nextLernSessionUebersetzung() {
		if (toLernIterator.hasNext()) {
			uebersetungCounter++;
			curentLesnSessionUebersetzung = toLernIterator.next();
			return true;
		}
		return false;
	}
		
	public void checkIfCorrect() {
		List<UebersetzungUebersetzung> fetchRecordsByMutterSprache = uebersetzungUebersetzungService.fetchRecordsByMutterSpracheList(curentLesnSessionUebersetzung.getUebersetzungUebersetzung().getMuttersprache());
		boolean correct = false;
		if (fetchRecordsByMutterSprache != null && !fetchRecordsByMutterSprache.isEmpty()) {
			for (UebersetzungUebersetzung uebersetzungUebersetzung : fetchRecordsByMutterSprache) {
				if (uebersetzungUebersetzung.getFremdsprache().getText().equals(uebersetzung)) {
					correct = true;
					break;
				}
			}
		} if (correct) {
			StringBuilder builder = new StringBuilder();
			for (UebersetzungUebersetzung uebersetzungUebersetzung : fetchRecordsByMutterSprache) {
				builder.append(uebersetzungUebersetzung.getFremdsprache().getText()).append(", ");
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Richtig", String.format("%s ist %s", curentLesnSessionUebersetzung.getUebersetzungUebersetzung().getMuttersprache().getText() , builder.toString().replaceAll(";\\s*$", ""))));
			if (!nextLernSessionUebersetzung()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LernSession abgeschlossen", ""));	
				curentLesnSessionUebersetzung = new LernSessionUebersetzungUebersetzung();
			}
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falsch", ""));
		}

		curentLesnSessionUebersetzung.setFehler(!correct);
		curentLesnSessionUebersetzung.setGelernt(true);
		saveLernSessionUebersetzungUebersetzung();
		uebersetzung = new String();
	}

	public void nextUebersetzung() {
		curentLesnSessionUebersetzung.setFehler(true);
		curentLesnSessionUebersetzung.setGelernt(true);
		saveLernSessionUebersetzungUebersetzung();
		if (!nextLernSessionUebersetzung()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LernSession abgeschlossen", ""));	
			curentLesnSessionUebersetzung = new LernSessionUebersetzungUebersetzung();
			toLern = null;
		}
	}
	public void saveLernSessionUebersetzungUebersetzung() {
		try {
			lernSessionUebersetzungUebresetunzgService.save(curentLesnSessionUebersetzung);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Speichern fehlgeschlagen", e.getMessage()));	
		}
	}

	public void initLernDataLERN_FAILD_AND_NOT_LERNED() {
		toLern = new HashSet<>();
		for (LernSessionUebersetzungUebersetzung lsuu : curentLernSession.getLernPool()) {
			if (lsuu != null && (lsuu.getFehler() || !lsuu.getGelernt()))
				toLern.add(lsuu);
		}
	}

	public void initLernDataLERN_ALL() {
		toLern = curentLernSession.getLernPool();
	}
	
	public LernType[] getLernTypes() {
		return LernType.values();
	}

	public LernType getSelectedLernType() {
		return selectedLernType;
	}

	public void setSelectedLernType(LernType selectedLernType) {
		this.selectedLernType = selectedLernType;
	}

	public Set<LernSessionUebersetzungUebersetzung> getToLern() {
		return toLern;
	}

	public void setToLern(Set<LernSessionUebersetzungUebersetzung> toLern) {
		this.toLern = toLern;
	}

	public LernSessionUebersetzungUebersetzung getCurentLesnSessionUebersetzung() {
		return curentLesnSessionUebersetzung;
	}

	public void setCurentLesnSessionUebersetzung(LernSessionUebersetzungUebersetzung curentLesnSessionUebersetzung) {
		this.curentLesnSessionUebersetzung = curentLesnSessionUebersetzung;
	}

	public String getUebersetzung() {
		return uebersetzung;
	}

	public void setUebersetzung(String uebersetzung) {
		this.uebersetzung = uebersetzung;
	}

	public LernSession getCurentLernSession() {
		return curentLernSession;
	}

	public void setCurentLernSession(LernSession curentLernSession) {
		this.curentLernSession = curentLernSession;
		toLern = null;
	}

	public Integer getUebersetungCounter() {
		return uebersetungCounter;
	}
}
