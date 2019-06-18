package ch.bytecrowd.voci.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ch.bytecrowd.voci.services.AbstractService;

public abstract class AbstractController<T> implements Serializable {

	private static final long serialVersionUID = -1271641843046979880L;
	
	private T selectedRecord;
	private List<T> records;

	public void saveSelected() {
		try {
			selectedRecord = getService().save(selectedRecord);
			records = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gespeichert", selectedRecord.toString()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
		}
	}
	
	public void deleteSelected() {
		if (selectedRecord != null)
		try {
			getService().delete(selectedRecord);
			records = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gelöscht", selectedRecord.toString()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getMessage()));
		}
	}

	public abstract void newRecord();
	
	public abstract AbstractService<T> getService();
	
	public T getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(T selectedSprache) {
		this.selectedRecord = selectedSprache;
	}
	
	public List<T> getRecords() {
		if (records == null) {
			records = getService().fetchRecords();
		}
		return records;
	}

}
