package de.hsrm.mi.web.bratservice;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerNichtVorhandenException;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerRepository;
import de.hsrm.mi.web.bratrepo.Braten;
import de.hsrm.mi.web.bratrepo.BratenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BratenServiceImpl implements BratenService {
    @Autowired
    BratenRepository bratenRepository;

    @Autowired
    BenutzerRepository benutzerRepository;

    /**
     * Liste der Braten in Datenbank
     */
    @Override
    public List<Braten> alleBraten() {
        return bratenRepository.findAll();
    }

    /**
     * Braten mit der angegebenen ID suchen
     */
    @Override
    public Optional<Braten> sucheBratenMitId(int id) {
        Optional<Braten> braten = bratenRepository.findById(id);
        if (!braten.isPresent()) {
            throw new BratenNichtVorhandenException();
        }
        braten.get().initFormValues();
        return braten;
    }

    /**
     * Uebergebenen Braten in der Datenbank anlegen
     * bzw. aktualisieren
     */
    @Override
    @Transactional
    public Braten editBraten(String loginname, Braten braten) throws BratenServiceException {
        Benutzer benutzer = benutzerRepository.findByLoginname(loginname);
        if (benutzer == null) {
            throw new BenutzerNichtVorhandenException();
        }
        try {
            braten.setAnbieter(benutzer);
            return bratenRepository.save(braten);
        } catch (IllegalArgumentException e) {
            throw new BratenServiceException();
        }
    }

    /**
     * Braten mit der angegebenen ID loeschen
     */
    @Override
    public void loescheBraten(int bratendatenid) {
        if (bratenRepository.findById(bratendatenid).isPresent()) {
            bratenRepository.deleteById(bratendatenid);
        } else {
            throw new BratenNichtVorhandenException();
        }
    }
}
