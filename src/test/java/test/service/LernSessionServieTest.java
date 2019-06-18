
package test.service;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;

import ch.bytecrowd.voci.model.Benutzer;
import ch.bytecrowd.voci.model.LernSession;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.BenutzerService;
import ch.bytecrowd.voci.services.LernSessionService;

public class LernSessionServieTest extends AbstractServieTest<LernSession> {

	private static final Logger LOG = Logger.getLogger(LernSessionServieTest.class);
	
	private LernSessionService lernSessionService = new LernSessionService();
	private BenutzerService benutzerService = new BenutzerService();

	@Override
	public void saveTest() {
		LernSession lernSession = new LernSession();
		Benutzer benutzer = new Benutzer();
		benutzer.setUsername("admin");
		benutzer.setPasswort("admin");
		addEntityManagerToService(benutzerService);
		try {
			benutzer = benutzerService.save(benutzer);
		} catch (Exception e) {
			LOG.error(String.format("faild to save new Benutzer: %s", benutzer), e);
			fail();
		}
		lernSession.setBenutzer(benutzer);
		lernSession = save(lernSession);
		assertThat(lernSession.getId(), notNullValue());
		assertThat(lernSession.getBenutzer(), notNullValue());
	}

	@Override
	public void deleteTest() {
		LernSession lernSession = new LernSession();
		delete(lernSession);
	}

	@Override
	public AbstractService<LernSession> getService() {
		return lernSessionService;
	}
}