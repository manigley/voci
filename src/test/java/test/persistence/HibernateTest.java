package test.persistence;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.bytecrowd.voci.interfaces.Scopeless;
import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.model.Uebersetzung;
import ch.bytecrowd.voci.model.UebersetzungUebersetzung;
import ch.bytecrowd.voci.producers.EntityManagerProducer;

@RunWith(CdiRunner.class)
@AdditionalClasses({ EntityManagerProducer.class })
public class HibernateTest {

	private final static Logger LOG = Logger.getLogger(HibernateTest.class);

	@Inject
	@Scopeless
	private EntityManager em;

	@Test
	public void initialisationTest() {

		Sprache deutsch = new Sprache();
		deutsch.setSprache("Deutsch");
		Sprache franz = new Sprache();
		franz.setSprache("Franz");

		Uebersetzung hallo_de = new Uebersetzung();
		hallo_de.setText("Hallo");
		hallo_de.setSprache(deutsch);
		Uebersetzung hallo_fr = new Uebersetzung();
		hallo_fr.setText("Bonjour");
		hallo_fr.setSprache(franz);

		UebersetzungUebersetzung uebersetzungUebresetzung = new UebersetzungUebersetzung();
		uebersetzungUebresetzung.setMuttersprache(hallo_de);
		uebersetzungUebresetzung.setFremdsprache(hallo_fr);
		try {
			em.getTransaction().begin();
			em.persist(deutsch);
			em.persist(franz);
			em.persist(hallo_de);
			em.persist(hallo_fr);
			em.persist(uebersetzungUebresetzung);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("save faild", e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

		List<UebersetzungUebersetzung> resultList = em.createQuery("FROM UebersetzungUebersetzung u WHERE u.muttersprache.text = :mutterSprache", UebersetzungUebersetzung.class)
				.setParameter("mutterSprache", hallo_de.getText()).getResultList();

		Assert.assertThat(resultList.isEmpty(), CoreMatchers.is(false));
		Assert.assertThat(resultList.get(0).getFremdsprache().getText(), CoreMatchers.is("Bonjour"));

	}
}
