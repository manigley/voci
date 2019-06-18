package test.service;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.SpracheService;

public class SpracheServieTest extends AbstractServieTest<Sprache> {

	private SpracheService spracheService = new SpracheService();

	@Override
	public void saveTest() {
		Sprache sprache = new Sprache();
		String randomString = getRandomString();
		sprache.setSprache(randomString);
		sprache = save(sprache);
		assertThat(sprache.getId(), notNullValue());
		assertThat(sprache.getSprache(), is(randomString));
	}

	@Override
	public void deleteTest() {
		Sprache sprache = new Sprache();
		String randomString = getRandomString();
		sprache.setSprache(randomString);
		delete(sprache);;
	}

	@Override
	public AbstractService<Sprache> getService() {
		return spracheService;
	}
}