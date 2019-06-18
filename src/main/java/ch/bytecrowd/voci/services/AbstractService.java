package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractService<T> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;

	@Inject
	private EntityManager em;
	
	public abstract List<T> fetchRecords();
	
	public T save(T record) throws Exception {
		try {
			getEm().getTransaction().begin();
			record = getEm().merge(record);
			getEm().flush();
			getEm().getTransaction().commit();
			return record;
		} catch (Exception e) {
			if (getEm().getTransaction().isActive())
				getEm().getTransaction().rollback();
			throw e;
		}
	}
	
	public void delete(T record) throws Exception {
		try {
			getEm().getTransaction().begin();
			record = getEm().merge(record);
			getEm().remove(record);
			getEm().flush();
			getEm().getTransaction().commit();
		} catch (Exception e) {
			if (getEm().getTransaction().isActive())
				getEm().getTransaction().rollback();
			throw e;
		}
	}

	public EntityManager getEm() {
		return em;
	}
}
