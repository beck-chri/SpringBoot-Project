package de.hsrm.mi.web.bratservice;

import de.hsrm.mi.web.bratrepo.Braten;

import java.util.List;
import java.util.Optional;

public interface BratenService {
    List<Braten> alleBraten();

    Optional<Braten> sucheBratenMitId(int id);

    Braten editBraten(String loginname, Braten braten);

    void loescheBraten(int bratendatenid);
}
