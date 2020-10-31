package de.hsrm.mi.web.bratrepo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hsrm.mi.web.bratenbank.benutzer.Benutzer;
import de.hsrm.mi.web.validation.GuteAdresse;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Braten {
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Benutzer anbieter;

    @GuteAdresse(message = "{address.invalid}")
    private String abholort;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{bbe.invalid}")
    @FutureOrPresent(message = "{bbe.past}")
    private LocalDate haltbarBis;

    @Size(max = 80, message = "{description.length}")
    @NotBlank(message = "{description.blank}")
    private String beschreibung;

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    int vgrad;

    @Transient
    @JsonIgnore
    private int[] veggieWerte;

    public Braten() {
        initFormValues();
    }

    public Braten(Benutzer anbieter, String abholort, LocalDate haltbarBis, String beschreibung) {
        this.anbieter = anbieter;
        this.abholort = abholort;
        this.haltbarBis = haltbarBis;
        this.beschreibung = beschreibung;
        initFormValues();
    }

    public Benutzer getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }

    public String getAbholort() {
        return abholort;
    }

    public LocalDate getHaltbarBis() {
        return haltbarBis;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getVgrad() {
        return vgrad;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public int[] getVeggieWerte() {
        return veggieWerte;
    }

    public void setAbholort(String abholort) {
        this.abholort = abholort;
    }

    public void setHaltbarBis(LocalDate haltbarBis) {
        this.haltbarBis = haltbarBis;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setVgrad(int vgrad) {
        this.vgrad = vgrad;
    }

    public void initFormValues() {
        this.veggieWerte = new int[]{0, 25, 50, 100};
    }

    public String toString() {
        String returnString = "";
        returnString += "Anbieter: " + getAnbieter().getVollname() + ", ";
        returnString += "Abholort: " + getAbholort() + ", ";
        returnString += "haltbar bis: " + getHaltbarBis() + ", ";
        returnString += "Beschreibung: " + getBeschreibung() + ", ";
        returnString += "Vegezitaritätsgrad: " + getVgrad();
        return returnString;
    }
}
