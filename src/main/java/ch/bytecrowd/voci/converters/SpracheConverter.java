package ch.bytecrowd.voci.converters;

import javax.inject.Named;

import ch.bytecrowd.voci.model.Sprache;

@Named("spracheConverter")
public class SpracheConverter extends GenericConverter<Sprache> {

	private static final long serialVersionUID = 8763206320118739151L;
	
	public SpracheConverter(Class<Sprache> clazz) {
		super(clazz);
	}
	
	public SpracheConverter() {
		this(Sprache.class);
	}
}