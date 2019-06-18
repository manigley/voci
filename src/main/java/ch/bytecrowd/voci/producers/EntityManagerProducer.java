package ch.bytecrowd.voci.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import ch.bytecrowd.voci.interfaces.Scopeless;

@ApplicationScoped
public class EntityManagerProducer {

	private final static Logger LOG = Logger.getLogger(EntityManagerProducer.class);
	private EntityManagerFactory emf = null;

	public void init(@Observes @Initialized(ApplicationScoped.class) Object o) {
		LOG.info("creating EntityManagerFactory");
		emf = Persistence.createEntityManagerFactory("pu");
	}
	
	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object o) {
		LOG.info("closing EntityManagerFactory");
		if (emf.isOpen())
			emf.close();
		LOG.info("EntityManagerFactory closed");
	}
	
	@Produces
	@RequestScoped
	public EntityManager produceEntityManager() {
		LOG.info("creating EntityManager");
		return emf.createEntityManager();
	}
	
	@Produces
	@Scopeless
	public EntityManager produceScopelessEntityManager() {
		return produceEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager em) {
		LOG.info("closing EntityManager");
		if (em.isOpen())
			em.close();
	}
	
	public void closeEntityScopelessManager(@Scopeless @Disposes EntityManager em) {
		closeEntityManager(em);
	}
}
