package de.hsrm.mi.web.bratrepo;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BratenRepository extends JpaRepository<Braten, Integer> {
    List<Braten> findByBeschreibungContainsIgnoringCase(String beschreibung);

    List<Braten> findByAnbieter(Benutzer anbieter);
}
