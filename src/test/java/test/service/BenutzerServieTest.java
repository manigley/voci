package test.service;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import ch.bytecrowd.voci.model.Benutzer;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.BenutzerService;

public class BenutzerServieTest extends AbstractServieTest<Benutzer> {

	private BenutzerService benutzerService = new BenutzerService();

	@Override
	public void saveTest() {
		Benutzer benutzer = new Benutzer();
		String randomString = getRandomString();
		benutzer.setUsername(randomString);
		benutzer.setPasswort(randomString);
		benutzer = save(benutzer);
		assertThat(benutzer.getId(), notNullValue());
		assertThat(benutzer.getUsername(), is(randomString));
		assertThat(benutzer.getPasswort(), is(not(randomString)));
	}

	@Override
	public void deleteTest() {
		Benutzer benutzer = new Benutzer();
		String randomString = getRandomString();
		benutzer.setUsername(randomString);
		benutzer.setPasswort(randomString);
		delete(benutzer);;
	}

	@Override
	public AbstractService<Benutzer> getService() {
		return benutzerService;
	}
}