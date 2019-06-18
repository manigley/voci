package test.service;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import ch.bytecrowd.voci.model.Kapitel;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.KapitelService;

public class KapitelServieTest extends AbstractServieTest<Kapitel> {

	private KapitelService kapitelService = new KapitelService();

	@Override
	public void saveTest() {
		Kapitel kapitel = new Kapitel();
		String randomString = getRandomString();
		kapitel.setKapitel(randomString);
		kapitel = save(kapitel);
		assertThat(kapitel.getId(), notNullValue());
		assertThat(kapitel.getKapitel(), is(randomString));
	}

	@Override
	public void deleteTest() {
		Kapitel kapitel = new Kapitel();
		String randomString = getRandomString();
		kapitel.setKapitel(randomString);
		delete(kapitel);;
	}

	@Override
	public AbstractService<Kapitel> getService() {
		return kapitelService;
	}
}