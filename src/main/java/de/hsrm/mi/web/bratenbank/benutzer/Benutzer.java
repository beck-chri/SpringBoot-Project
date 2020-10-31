package de.hsrm.mi.web.bratenbank.benutzer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.hsrm.mi.web.bratrepo.Braten;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Benutzer {
    @OneToMany(mappedBy = "anbieter")
    @JsonIgnore
    private Collection<Braten> angebote = new HashSet<>();

    @NotBlank(message = "{notBlank}")
    @Column(unique = true)
    private String loginname;

    @Size(min = 3, message = "{password.length}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwort;

    @NotBlank(message = "{notBlank}")
    private String vollname;

    private boolean nutzungsbedingungenok;

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Version
    @JsonIgnore
    private long version;

    public Benutzer() {

    }

    public Benutzer(String loginname, String passwort, String vollname) {
        this.loginname = loginname;
        this.passwort = passwort;
        this.vollname = vollname;
    }

    public Collection<Braten> getAngebote() {
        return angebote;
    }

    public String getLoginname() {
        return loginname;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getVollname() {
        return vollname;
    }

    public boolean isNutzungsbedingungenok() {
        return nutzungsbedingungenok;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setVollname(String vollname) {
        this.vollname = vollname;
    }

    public void setNutzungsbedingungenok(boolean nutzungsbedingungenok) {
        this.nutzungsbedingungenok = nutzungsbedingungenok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benutzer benutzer = (Benutzer) o;
        return Objects.equals(loginname, benutzer.loginname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginname);
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "loginname='" + loginname + '\'' +
                ", passwort='" + passwort + '\'' +
                ", vollname='" + vollname + '\'' +
                ", nutzungsbedingungenok=" + nutzungsbedingungenok +
                '}';
    }
}