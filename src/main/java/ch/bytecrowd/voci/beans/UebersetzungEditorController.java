package ch.bytecrowd.voci.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.model.UebersetzungUebersetzung;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.UebersetzungService;
import ch.bytecrowd.voci.services.UebersetzungUebersetzungService;

@ViewScoped
@Named("uebersetzungEditorController")
public class UebersetzungEditorController extends AbstractController<UebersetzungUebersetzung> implements Serializable {

	private static final long serialVersionUID = -1271641843046979880L;

	@Inject
	private UebersetzungUebersetzungService uebersetzungUebersetzungService;
	@Inject
	private UebersetzungService uebersetzungService;

	public List<String> autoCompleteMuttersprache(String query) {
		return autoComplete(query, getSelectedRecord().getMuttersprache().getSprache());
	}
	
	public List<String> autoCompleteFremdsprache(String query) {
		return autoComplete(query, getSelectedRecord().getFremdsprache().getSprache());
	}

	private List<String> autoComplete(String query, Sprache sprache) {
		List<String> list = new ArrayList<>();
		list = uebersetzungService.findUebersetzungTextBySprachenWhereTextLike(sprache, query);
		list.remove(query);
		list.add(query);
		return list;
	}
	
	@Override
	public void newRecord() {
		setSelectedRecord(new UebersetzungUebersetzung());
	}
	
	@Override
	public void saveSelected() {
		UebersetzungUebersetzung uebersetzungUebersetzung = new UebersetzungUebersetzung();
		if (getSelectedRecord().getId() == null) {
			uebersetzungUebersetzung.setFremdsprache(getSelectedRecord().getFremdsprache());
			uebersetzungUebersetzung.setMuttersprache(getSelectedRecord().getMuttersprache());
			uebersetzungUebersetzung.setKapitel(getSelectedRecord().getKapitel());
			super.saveSelected();
			setSelectedRecord(uebersetzungUebersetzung);
		} else {
			super.saveSelected();
		}
	}

	@Override
	public AbstractService<UebersetzungUebersetzung> getService() {
		return uebersetzungUebersetzungService;
	}	
}
