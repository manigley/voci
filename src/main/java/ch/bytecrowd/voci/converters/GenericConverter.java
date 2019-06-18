package ch.bytecrowd.voci.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.bytecrowd.voci.interfaces.Keyable;

public class GenericConverter<T extends Keyable> implements Converter, Serializable {

	private static final long serialVersionUID = 8763206320118739151L;
	
	@Inject
	private EntityManager em;

	private Class<T> entity;
	
	public GenericConverter(Class<T> clazz) {
		 this.entity = clazz;

	}
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			return em.find(entity, Long.valueOf(value));
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Keyable) object).getId());
		} else {
			return null;
		}
	}
}
