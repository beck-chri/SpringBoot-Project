package de.hsrm.mi.web.login;

import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.bratenbank.benutzer.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(names = {"loggedInUser"})
public class LoginController {
    @Autowired
    BenutzerService benutzerService;

    /**
     * Session-Attribut initialisieren
     */
    @ModelAttribute("loggedInUser")
    public void initLoggedInUser(Model m) {
        m.addAttribute("loggedInUser", new Benutzer());
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    /**
     * Ueberpruefen, ob Benutzer in Datenbank existiert
     * und bei Erfolg Listen-Formular anzeigen
     */
    @PostMapping("/login")
    public String postLogin(Model m, @RequestParam String username, @RequestParam String password,
                            @ModelAttribute("loggedInUser") Benutzer benutzer) {
        if (benutzerService.findeBenutzer(username) != null){
            if (benutzerService.pruefeLogin(username, password)) {
                benutzer.setLoginname(username);
                return "redirect:/braten/angebot";
            } else {
                m.addAttribute("passwordWrong", true);
                m.addAttribute("username", username);
                m.addAttribute("correctPassword", benutzerService.ermittlePasswort(username));
            }
        } else {
            m.addAttribute("userNotFound", true);
            m.addAttribute("username", username);
        }
        return "login";
    }
}
