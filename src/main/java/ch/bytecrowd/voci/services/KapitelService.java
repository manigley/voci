package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import ch.bytecrowd.voci.model.Kapitel;

@RequestScoped
public class KapitelService extends AbstractService<Kapitel> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;
	
	public List<Kapitel> fetchRecords() {
		return getEm().createQuery("FROM Kapitel k ORDER BY k.kapitel", Kapitel.class).getResultList();
	}
}
