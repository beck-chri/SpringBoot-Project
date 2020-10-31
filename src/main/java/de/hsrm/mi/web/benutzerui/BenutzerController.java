package de.hsrm.mi.web.benutzerui;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzernameSchonVergebenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BenutzerController {

    @Autowired
    BenutzerService benutzerService;

    /**
     * Neuen Benutzer im Registrierungsformular anlegen
     */
    @GetMapping("/benutzer")
    public String getBenutzer(Model m) {
        m.addAttribute("benutzer", new Benutzer());
        m.addAttribute("tosError", false);
        m.addAttribute("loginnameTaken", false);
        return "benutzerui/benutzerregistrierung";
    }

    /**
     * Benutzer in Datenbank speichern und Listen-Formular anzeigen
     */
    @PostMapping("/benutzer")
    public String postBenutzer(Model m, @Valid @ModelAttribute("benutzer") Benutzer benutzer,
                               BindingResult result) {
        m.addAttribute("tosError", !benutzer.isNutzungsbedingungenok());
        if (result.hasErrors() || benutzer.isNutzungsbedingungenok() == false) {
            m.addAttribute("benutzer", benutzer);
            return "benutzerui/benutzerregistrierung";
        }
        try {
            benutzerService.registriereBenutzer(benutzer);
        } catch (BenutzernameSchonVergebenException e) {
            m.addAttribute("loginnameTaken", true);
            m.addAttribute("benutzer", benutzer);
            return "benutzerui/benutzerregistrierung";
        }
        return "redirect:/login";
    }
}