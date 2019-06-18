package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import ch.bytecrowd.voci.model.Benutzer;
import ch.bytecrowd.voci.model.LernSession;

@RequestScoped
public class LernSessionService extends AbstractService<LernSession> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;

	public List<LernSession> fetchRecords() {
		return getEm().createQuery("FROM LernSession ls ORDER BY ls.id", LernSession.class).getResultList();
	}

	public List<LernSession> fetchLernsessionsByBenutzer(Benutzer benutzer) {
		return getEm()
				.createQuery("FROM LernSession ls where ls.benutzer.id = :idBenutzer ORDER BY ls.id", LernSession.class)
				.setParameter("idBenutzer", benutzer.getId())
				.getResultList();
	}
}
