package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.model.Uebersetzung;

@RequestScoped
public class UebersetzungService extends AbstractService<Uebersetzung> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;

	public List<String> findUebersetzungTextBySprachenWhereTextLike(Sprache sprache, String text) {
		return getEm().createQuery("SELECT u.text FROM Uebersetzung u WHERE u.sprache.id = :idSprache AND u.text LIKE :text ORDER BY u.text",
				String.class)
				.setParameter("idSprache", sprache.getId())
				.setParameter("text", "%" + text + "%").getResultList();
	}
	
	public Uebersetzung findUebersetzungBySpracheWhereTextLike(Sprache sprache, String text) {
		List<Uebersetzung> list = getEm().createQuery("FROM Uebersetzung u WHERE u.sprache.id = :idSprache AND u.text LIKE :text",
				Uebersetzung.class)
				.setParameter("idSprache", sprache.getId())
				.setParameter("text", "%" + text + "%").getResultList();
		if (list == null || list.isEmpty())
			return new Uebersetzung(text, sprache);
		return list.get(0);
	}
	
	public Uebersetzung findUebersetzungBySpracheWhereTextEqual(Sprache sprache, String text) {
		List<Uebersetzung> list = getEm().createQuery("FROM Uebersetzung u WHERE u.sprache.id = :idSprache AND u.text = :text",
				Uebersetzung.class)
				.setParameter("idSprache", sprache.getId())
				.setParameter("text", text).getResultList();
		if (list == null || list.isEmpty())
			return new Uebersetzung(text, sprache);
		return list.get(0);
	}
	
	@Override
	public List<Uebersetzung> fetchRecords() {
		return getEm().createQuery("FROM Uebersetzung u order by u.text",
				Uebersetzung.class).getResultList();
	}
}
