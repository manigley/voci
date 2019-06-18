package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import ch.bytecrowd.voci.model.Kapitel;
import ch.bytecrowd.voci.model.Uebersetzung;
import ch.bytecrowd.voci.model.UebersetzungUebersetzung;

@RequestScoped
public class UebersetzungUebersetzungService extends AbstractService<UebersetzungUebersetzung> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;
	
	@Inject
	private UebersetzungService uebersetzungService;
	
	@Override
	public UebersetzungUebersetzung save(UebersetzungUebersetzung record) throws Exception {
		Uebersetzung muttersprache = null;
		Uebersetzung fremdsprache = null;
		
		record.setMuttersprache(uebersetzungService.findUebersetzungBySpracheWhereTextEqual(record.getMuttersprache().getSprache(), record.getMuttersprache().getText()));
		record.setFremdsprache(uebersetzungService.findUebersetzungBySpracheWhereTextEqual(record.getFremdsprache().getSprache(), record.getFremdsprache().getText()));
		
		try {
			getEm().getTransaction().begin();
			muttersprache = getEm().merge(record.getMuttersprache());
			fremdsprache = getEm().merge(record.getFremdsprache());
			record.setMuttersprache(muttersprache);
			record.setFremdsprache(fremdsprache);
			record = getEm().merge(record);
			getEm().flush();
			getEm().getTransaction().commit();
		} catch (Exception e) {
			if (getEm().getTransaction().isActive())
				getEm().getTransaction().rollback();
			throw e;
		}
		return record;
	}

	public List<UebersetzungUebersetzung> fetchRecordsByKapitelList(List<Kapitel> kapitelList) {
		return getEm().createQuery("FROM UebersetzungUebersetzung uu JOIN FETCH uu.muttersprache m JOIN FETCH uu.fremdsprache f JOIN FETCH f.sprache JOIN FETCH m.sprache WHERE uu.kapitel in (:kapitelList)",
				UebersetzungUebersetzung.class)
				.setParameter("kapitelList", kapitelList)
				.getResultList();
	}
	
	public List<UebersetzungUebersetzung> fetchRecordsByMutterSpracheList(Uebersetzung muttersprache) {
		return getEm().createQuery("FROM UebersetzungUebersetzung uu JOIN FETCH uu.kapitel k JOIN FETCH uu.muttersprache m JOIN FETCH uu.fremdsprache f JOIN FETCH f.sprache JOIN FETCH m.sprache WHERE m.id = :idMutterSprache",
				UebersetzungUebersetzung.class)
				.setParameter("idMutterSprache", muttersprache.getId())
				.getResultList();
	}
	
	@Override
	public List<UebersetzungUebersetzung> fetchRecords() {
		return getEm().createQuery("FROM UebersetzungUebersetzung uu JOIN FETCH uu.muttersprache m JOIN FETCH uu.fremdsprache f JOIN FETCH f.sprache JOIN FETCH m.sprache JOIN FETCH uu.kapitel",
				UebersetzungUebersetzung.class).getResultList();
	}
}
