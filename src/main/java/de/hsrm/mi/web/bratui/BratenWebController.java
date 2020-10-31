package de.hsrm.mi.web.bratui;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratrepo.Braten;
import de.hsrm.mi.web.bratservice.BratenNichtVorhandenException;
import de.hsrm.mi.web.bratservice.BratenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/braten")
@SessionAttributes(names = {"loggedInUser"})
public class BratenWebController {

    @Autowired
    BratenService bratenService;

    /**
     * Liste der Angebote anzeigen
     */
    @GetMapping("/angebot")
    public String getAngebotListe(Model m) {
        m.addAttribute("angebote", bratenService.alleBraten());
        return "braten/liste";
    }

    /**
     * Neues Element im Bearbeiten-Formular anzeigen
     */
    @GetMapping("/angebot/neu")
    public String getAngebotNeu(Model m) {
        m.addAttribute("angebotform", new Braten());
        return "braten/bearbeiten";
    }

    /**
     * Neues Angebot in Liste posten
     */
    @PostMapping("/angebot/neu")
    public String postAngebotNeu(@ModelAttribute("angebotform") @Valid Braten braten, BindingResult result,
                                 @ModelAttribute("loggedInUser") Benutzer loggedInUser) {
        if (result.hasErrors()) {
            return "braten/bearbeiten";
        }
        bratenService.editBraten(loggedInUser.getLoginname(), braten);
        return "redirect:/braten/angebot";
    }

    /**
     * Angebot per ID aus Liste entfernen und im Bearbeiten-Formular anzeigen
     */
    @GetMapping("angebot/{id}")
    public String editAngebot(Model m, @PathVariable int id) {
        try {
            Optional<Braten> braten = bratenService.sucheBratenMitId(id);
            m.addAttribute("angebotform", braten.get());
            bratenService.loescheBraten(id);
            return "braten/bearbeiten";
        } catch (BratenNichtVorhandenException e) {
            return "redirect:/braten/angebot";
        }
    }

    /**
     * Angebot per ID aus Liste entfernen und Liste anzeigen
     */
    @GetMapping("angebot/{id}/del")
    public String deleteAngebot(@PathVariable int id) {
        bratenService.loescheBraten(id);
        return "redirect:/braten/angebot";
    }
}