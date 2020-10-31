package de.hsrm.mi.web.api;

import de.hsrm.mi.web.bratenbank.benutzer.BenutzerNichtVorhandenException;
import de.hsrm.mi.web.bratrepo.Braten;
import de.hsrm.mi.web.bratservice.BratenNichtVorhandenException;
import de.hsrm.mi.web.bratservice.BratenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/braten")
public class BratenRestApi {

    @Autowired
    BratenService bratenService;

    @GetMapping("")
    public List<Braten> getAlleBraten() {
        return bratenService.alleBraten();
    }

    @GetMapping("/{id}")
    public Optional<Braten> getBraten(@PathVariable int id) throws BratenNichtVorhandenException {
        return bratenService.sucheBratenMitId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBraten(@PathVariable int id) throws BratenNichtVorhandenException {
        bratenService.loescheBraten(id);
    }

    @PostMapping(value = "")
    public Braten postBraten(@RequestBody Braten braten, @RequestParam String loginname)
            throws BenutzerNichtVorhandenException, BratenNichtVorhandenException {
        return bratenService.editBraten(loginname, braten);
    }
}
