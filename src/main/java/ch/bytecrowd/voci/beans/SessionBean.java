package ch.bytecrowd.voci.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ch.bytecrowd.voci.model.Benutzer;
import ch.bytecrowd.voci.services.BenutzerService;

@SessionScoped
@Named("sessionBean")
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 4419656173666696872L;
	private static final Logger LOG = Logger.getLogger(SessionBean.class);
	@Inject
	private BenutzerService benutzerService;

	private Benutzer benutzer = null;

	public void login(String benutzername, String passwort) {
		System.out.println("benutzername: " + benutzername);
		try {
			Benutzer user = new Benutzer(benutzername, passwort);
			// [Dummy Method] multi user will be implemented when needed
			benutzerService.saveIfFirstUser(user.getUsername(), user.getPasswort());
			user = benutzerService.findUserByUsernameAndPass(user.getUsername(), user.getPasswort());
			if (user != null) {
				LOG.info(String.format("user logged in: %s", user.getUsername()));
				benutzer = user;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						String.format("Hallo %s", user.getUsername()), ""));
			} else {
				throw new Exception(String.format("login denied for %s (wrong password)", benutzername));
			}
		} catch (Exception e) {
			LOG.error(String.format("Login faild: %s", e.getMessage()));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					String.format("Login denied for %s", benutzername), ""));
		}
	}

	public void logout() {
		benutzer = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}
}
