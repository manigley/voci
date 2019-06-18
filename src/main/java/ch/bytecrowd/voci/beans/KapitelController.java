package ch.bytecrowd.voci.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bytecrowd.voci.model.Kapitel;
import ch.bytecrowd.voci.services.AbstractService;
import ch.bytecrowd.voci.services.KapitelService;

@ViewScoped
@Named("kapitelController")
public class KapitelController extends AbstractController<Kapitel> implements Serializable {

	private static final long serialVersionUID = -1271641843046979880L;
	
	@Inject
	private KapitelService kapitelService;
	
	public void newRecord() {
		setSelectedRecord(new Kapitel());
	}

	@Override
	public AbstractService<Kapitel> getService() {
		return kapitelService;
	}
}
