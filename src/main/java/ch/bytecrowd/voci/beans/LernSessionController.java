package ch.bytecrowd.voci.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bytecrowd.voci.model.Kapitel;
import ch.bytecrowd.voci.model.LernSession;
import ch.bytecrowd.voci.model.LernSessionUebersetzungUebersetzung;
import ch.bytecrowd.voci.model.UebersetzungUebersetzung;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.LernSessionService;
import ch.bytecrowd.voci.services.UebersetzungUebersetzungService;

@ViewScoped
@Named("lernSessionController")
public class LernSessionController extends AbstractController<LernSession> implements Serializable {

	private static final long serialVersionUID = -1271641843046979880L;

	private List<LernSession> currentBenutzerLernSessions = null;

	private List<Kapitel> selectedKapitelList = new ArrayList<>();

	@Inject
	private LernSessionService lernSessionService;
	@Inject
	private UebersetzungUebersetzungService uebersetzungUebersetzungService;
	
	@Inject
	private SessionBean sessionBean;
	
	public void newRecord() {
		selectedKapitelList = new ArrayList<>();
		super.setSelectedRecord(new LernSession());
	}
	
	@Override
	public void saveSelected() {
		fillLernPool();
		getSelectedRecord().setBenutzer(sessionBean.getBenutzer());
		super.saveSelected();
		currentBenutzerLernSessions =  null;
	}
	
	private void fillLernPool() {
		getSelectedRecord().setLernPool(new HashSet<LernSessionUebersetzungUebersetzung>());
		if (selectedKapitelList != null && !selectedKapitelList.isEmpty()) {
			List<UebersetzungUebersetzung> fetchRecordsByKapitelList = uebersetzungUebersetzungService.fetchRecordsByKapitelList(selectedKapitelList);
			for (UebersetzungUebersetzung uu : fetchRecordsByKapitelList) {
				getSelectedRecord().getLernPool().add(new LernSessionUebersetzungUebersetzung(uu));
			}
		}
	}

	@Override
	public void deleteSelected() {
		super.deleteSelected();
		currentBenutzerLernSessions =  null;
	}

	public List<LernSession> getCurrentBenutzerLernSessions() {
		if (currentBenutzerLernSessions == null)
			currentBenutzerLernSessions = lernSessionService.fetchLernsessionsByBenutzer(sessionBean.getBenutzer());
		return currentBenutzerLernSessions;
	}
	
	@Override
	public void setSelectedRecord(LernSession selectedRecord) {
		selectedKapitelList = new ArrayList<>();
		for(LernSessionUebersetzungUebersetzung uu : selectedRecord.getLernPool()) {
			if (!selectedKapitelList.contains(uu.getUebersetzungUebersetzung().getKapitel()))
				selectedKapitelList.add(uu.getUebersetzungUebersetzung().getKapitel());
		}
		super.setSelectedRecord(selectedRecord);
	}

	@Override
	public AbstractService<LernSession> getService() {
		return lernSessionService;
	}

	public List<Kapitel> getSelectedKapitelList() {
		return selectedKapitelList;
	}

	public void setSelectedKapitelList(List<Kapitel> selectedKapitelList) {
		this.selectedKapitelList = selectedKapitelList;
	}
	
}
