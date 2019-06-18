package ch.bytecrowd.voci.converters;

import javax.inject.Named;

import ch.bytecrowd.voci.model.Kapitel;

@Named("kapitelConverter")
public class KapitelConverter extends GenericConverter<Kapitel> {

	private static final long serialVersionUID = 8763206320118739151L;
	
	public KapitelConverter(Class<Kapitel> clazz) {
		super(clazz);
	}
	
	public KapitelConverter() {
		this(Kapitel.class);
	}
}
