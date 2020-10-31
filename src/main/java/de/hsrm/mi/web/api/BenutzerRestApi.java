package de.hsrm.mi.web.api;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzernameSchonVergebenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/benutzer")
public class BenutzerRestApi {

    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/{loginname}")
    public Benutzer getBenutzer(@PathVariable String loginname) {
        return benutzerService.findeBenutzer(loginname);
    }

    @PostMapping(value = "")
    public Benutzer postBenutzer(@RequestBody Benutzer neuerBenutzer) throws BenutzernameSchonVergebenException {
        return benutzerService.registriereBenutzer(neuerBenutzer);
    }
}
