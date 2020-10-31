package de.hsrm.mi.web.bratenbank.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class BenutzerServiceImpl implements BenutzerService {

    @Autowired
    BenutzerRepository benutzerRepository;

    /**
     * Ueberpruefen, ob mitgegebenes Passwort
     * mit dem in Datenbank uebereinstimmt
     */
    public boolean pruefeLogin(String loginname, String passwort) {
        if (ermittlePasswort(loginname).equals(passwort)) {
            return true;
        }
        return false;
    }

    /**
     * Passwort aus Datenbank abfragen
     */
    public String ermittlePasswort(String loginname) {
        Benutzer benutzerInDatenbank = findeBenutzer(loginname);
        if (benutzerInDatenbank != null) {
            return benutzerInDatenbank.getPasswort();
        }
        return null;
    }

    /**
     * Neuen Benutzer in Datenbank speichern
     */
    @Override
    public Benutzer registriereBenutzer(Benutzer neubenutzer) {
        if (findeBenutzer(neubenutzer.getLoginname()) != null) {
            throw new BenutzernameSchonVergebenException(neubenutzer.getLoginname());
        }
        return benutzerRepository.save(neubenutzer);
    }

    /**
     * Benutzer ueber loginname in Datenbank finden
     */
    @Override
    public Benutzer findeBenutzer(String loginname) {
        Benutzer benutzer = benutzerRepository.findByLoginname(loginname);
        if (benutzer == null) {
            return null;
        }
        return benutzer;
    }
}
