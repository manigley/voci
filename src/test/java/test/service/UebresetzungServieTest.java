package test.service;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;

import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.model.Uebersetzung;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.KapitelService;
import ch.bytecrowd.voci.services.SpracheService;
import ch.bytecrowd.voci.services.UebersetzungService;

public class UebresetzungServieTest extends AbstractServieTest<Uebersetzung> {

	private final static Logger LOG = Logger.getLogger(UebresetzungServieTest.class);
	
	private UebersetzungService uebersetzungService = new UebersetzungService();
	private Sprache muttersprache;
	private Sprache fremdsprache;
	@Override
	public void before() {
		super.before();
		SpracheService spracheService = new SpracheService();
		addEntityManagerToService(spracheService);
		KapitelService kapitelService = new KapitelService();
		addEntityManagerToService(kapitelService);
		
		muttersprache = new Sprache();
		muttersprache.setSprache("Deutsch");

		fremdsprache = new Sprache();
		fremdsprache.setSprache("Deutsch");

		try {
			muttersprache = spracheService.save(muttersprache);
			fremdsprache = spracheService.save(fremdsprache);
		} catch (Exception e) {
			fail();
		}
	}

	@Override
	public void saveTest() {
		Uebersetzung mutterspracheUebersetzung  = new Uebersetzung();
		Uebersetzung fremdspracheUebersetzung  = new Uebersetzung();
		mutterspracheUebersetzung = new Uebersetzung();
		mutterspracheUebersetzung.setSprache(muttersprache);
		mutterspracheUebersetzung.setText("Hallo du Sau");
		fremdspracheUebersetzung = new Uebersetzung();
		fremdspracheUebersetzung.setSprache(fremdsprache);
		fremdspracheUebersetzung.setText("Salut, vous");

		save(mutterspracheUebersetzung);
		save(fremdspracheUebersetzung);
	}

	@Override
	public void deleteTest() {
		Uebersetzung mutterspracheUebersetzung  = new Uebersetzung();
		Uebersetzung fremdspracheUebersetzung  = new Uebersetzung();
		mutterspracheUebersetzung = new Uebersetzung();
		mutterspracheUebersetzung.setSprache(muttersprache);
		mutterspracheUebersetzung.setText("Hallo du Sau");
		fremdspracheUebersetzung = new Uebersetzung();
		fremdspracheUebersetzung.setSprache(fremdsprache);
		fremdspracheUebersetzung.setText("Salut, vous");

		delete(mutterspracheUebersetzung);
		delete(fremdspracheUebersetzung);
	}

	@Override
	public AbstractService<Uebersetzung> getService() {
		return uebersetzungService;
	}
}