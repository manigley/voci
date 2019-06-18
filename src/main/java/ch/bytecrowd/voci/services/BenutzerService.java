package ch.bytecrowd.voci.services;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.apache.log4j.Logger;

import ch.bytecrowd.voci.model.Benutzer;

@RequestScoped
public class BenutzerService extends AbstractService<Benutzer> implements Serializable {

	private static final long serialVersionUID = -7882307167548132582L;
	private final static Logger LOG = Logger.getLogger(BenutzerService.class);
	
	public List<Benutzer> fetchRecords() {
		return getEm().createQuery("FROM Benutzer b ORDER BY b.username", Benutzer.class).getResultList();
	}
	
	public Benutzer findUserByUsernameAndPass(String benutzername, String passHashed) throws Exception {

		return getEm().createQuery("FROM Benutzer b WHERE b.username = :benutzername and b.passwort = :pass", Benutzer.class)
				.setParameter("benutzername", benutzername)
				.setParameter("pass", passHashed)
				.getSingleResult();
	}
	
	public void saveIfFirstUser(String benutzername, String passHashed) throws Exception {
		List<Benutzer> records = fetchRecords();
		try {
			if (records == null || records.isEmpty()) {
				Benutzer b = new Benutzer(benutzername, "admin");
				b.setHaschedPasswort(passHashed);
				save(b);
				LOG.info(String.format("first user created: %s", benutzername));
			}
		} catch (Exception e) {
			LOG.info(String.format("faild to create the first user: %s", benutzername));
			throw e;
		}	
	}
}
