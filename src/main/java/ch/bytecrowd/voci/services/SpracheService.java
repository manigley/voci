package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import ch.bytecrowd.voci.model.Sprache;

@RequestScoped
public class SpracheService extends AbstractService<Sprache> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;
	
	public List<Sprache> fetchRecords() {
		return getEm().createQuery("FROM Sprache s ORDER BY s.sprache", Sprache.class).getResultList();
	}
}
