package test.service;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.bytecrowd.voci.interfaces.Keyable;
import ch.bytecrowd.voci.interfaces.Scopeless;
import ch.bytecrowd.voci.producers.EntityManagerProducer;
import ch.bytecrowd.voci.services.AbstractService;

@RunWith(CdiRunner.class)
@AdditionalClasses({ EntityManagerProducer.class })
public abstract class AbstractServieTest<T extends Keyable> {

	private final static Logger LOG = Logger.getLogger(AbstractServieTest.class);

	@Inject
	@Scopeless
	private EntityManager em;
	
	protected abstract AbstractService<T> getService();
	
	@Test
	public abstract void saveTest();
	
	@Test
	public abstract void deleteTest();
	
	@Before
	public void before() {
		addEntityManagerToService(getService());
	}

	public void addEntityManagerToService(AbstractService<?> service) {
		try {
			Field declaredField = service.getClass().getSuperclass().getDeclaredField("em");
			declaredField.setAccessible(true);
			declaredField.set(service, em);
		} catch (Exception e) {
			LOG.error("adding EntityManager faild", e);
			fail();
		}
	}
	
	public T save(T record) {
		try {
			record = getService().save(record);
		} catch (Exception e) {
			LOG.error("save faild", e);
			fail();
		}
		assertThat(record.getId(), notNullValue());
		return record;
	}

	@SuppressWarnings("unchecked")
	public void delete(T record) {
		List<T> resultList = null;
		try {
			record = getService().save(record);
			record = (T) getService().getEm().find(record.getClass() ,record.getId());
			getService().delete(record);
			resultList = getService().getEm().createQuery("from " + record.getClass().getSimpleName() +" s where s.id = :id").setParameter("id", record.getId()).getResultList();
		} catch (Exception e) {
			LOG.error("save faild", e);
			fail();
		}
		assertThat(resultList, notNullValue());
		assertThat(resultList.isEmpty(), is(true));
	}

	public String getRandomString() {
		return new BigInteger(255, new SecureRandom()).toString(32);
	}
}
