package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import ch.bytecrowd.voci.model.LernSessionUebersetzungUebersetzung;

@RequestScoped
public class LernSessionUebersetzungUebresezungService extends AbstractService<LernSessionUebersetzungUebersetzung> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;
	
	public List<LernSessionUebersetzungUebersetzung> fetchRecords() {
		return getEm().createQuery("FROM LernSessionUebersetzungUebersetzung s ORDER BY s.id", LernSessionUebersetzungUebersetzung.class).getResultList();
	}
}
