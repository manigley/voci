package test.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import ch.bytecrowd.voci.model.Kapitel;
import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.model.Uebersetzung;
import ch.bytecrowd.voci.model.UebersetzungUebersetzung;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.KapitelService;
import ch.bytecrowd.voci.services.SpracheService;
import ch.bytecrowd.voci.services.UebersetzungService;
import ch.bytecrowd.voci.services.UebersetzungUebersetzungService;

public class UebresetzungUebersetzungServieTest extends AbstractServieTest<UebersetzungUebersetzung> {

	private final static Logger LOG = Logger.getLogger(UebresetzungUebersetzungServieTest.class);
	
	private UebersetzungUebersetzungService uebersetzungUebersetzungService = new UebersetzungUebersetzungService();
	private Kapitel kapitel = null;
	private Uebersetzung mutterspracheUebersetzung = null;
	private Uebersetzung fremdspracheUebersetzung = null;

	@Override
	public void before() {
		super.before();
		SpracheService spracheService = new SpracheService();
		addEntityManagerToService(spracheService);
		KapitelService kapitelService = new KapitelService();
		addEntityManagerToService(kapitelService);
		UebersetzungService uebersetzungService = new UebersetzungService();
		addEntityManagerToService(uebersetzungService);
		try {
			Field declaredField = uebersetzungUebersetzungService.getClass().getDeclaredField("uebersetzungService");
			declaredField.setAccessible(true);
			declaredField.set(uebersetzungUebersetzungService, uebersetzungService);
		} catch (Exception e) {
			LOG.error("adding uebersetzungService do uebersetzungUebersetzungService faild", e);
			fail();
		}

		Sprache muttersprache = new Sprache();
		muttersprache.setSprache("Deutsch");

		Sprache fremdsprache = new Sprache();
		fremdsprache.setSprache("Deutsch");

		kapitel = new Kapitel();
		kapitel.setKapitel("Kapitel 01");

		try {
			muttersprache = spracheService.save(muttersprache);
			fremdsprache = spracheService.save(fremdsprache);
			kapitel = kapitelService.save(kapitel);
		} catch (Exception e) {
			fail();
		}

		mutterspracheUebersetzung = new Uebersetzung();
		mutterspracheUebersetzung.setSprache(muttersprache);
		mutterspracheUebersetzung.setText("Hallo du Sau");

		fremdspracheUebersetzung = new Uebersetzung();
		fremdspracheUebersetzung.setSprache(fremdsprache);
		fremdspracheUebersetzung.setText("Salut, vous");

	}

	@Override
	public void saveTest() {
		UebersetzungUebersetzung uebersetzungUebersetzung = new UebersetzungUebersetzung();
		uebersetzungUebersetzung.setMuttersprache(mutterspracheUebersetzung);
		uebersetzungUebersetzung.setFremdsprache(fremdspracheUebersetzung);
		uebersetzungUebersetzung.setKapitel(kapitel);

		try {
			uebersetzungUebersetzung = uebersetzungUebersetzungService.save(uebersetzungUebersetzung);
		} catch (Exception e) {
			LOG.error(String.format("save UebersetzungUebersetzung faild for %s", uebersetzungUebersetzung.toString()), e);
			fail();
		}

		assertThat(uebersetzungUebersetzung.getId(), notNullValue());
		assertThat(uebersetzungUebersetzung.getMuttersprache().getId(), notNullValue());
		assertThat(uebersetzungUebersetzung.getFremdsprache().getId(), notNullValue());
	}

	@Override
	public void deleteTest() {
		UebersetzungUebersetzung uebersetzungUebersetzung = new UebersetzungUebersetzung();
		uebersetzungUebersetzung.setMuttersprache(mutterspracheUebersetzung);
		uebersetzungUebersetzung.setFremdsprache(fremdspracheUebersetzung);
		uebersetzungUebersetzung.setKapitel(kapitel);

		uebersetzungUebersetzung = save(uebersetzungUebersetzung);
		delete(uebersetzungUebersetzung);
	}

	@Override
	public AbstractService<UebersetzungUebersetzung> getService() {
		return uebersetzungUebersetzungService;
	}
}