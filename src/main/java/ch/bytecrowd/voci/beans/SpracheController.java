package ch.bytecrowd.voci.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bytecrowd.voci.model.Sprache;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.SpracheService;

@ViewScoped
@Named("spracheController")
public class SpracheController extends AbstractController<Sprache> implements Serializable {

	private static final long serialVersionUID = -1271641843046979880L;

	@Inject
	private SpracheService spracheService;

	public void newRecord() {
		setSelectedRecord(new Sprache());
	}

	@Override
	public AbstractService<Sprache> getService() {
		return spracheService;
	}	
}
