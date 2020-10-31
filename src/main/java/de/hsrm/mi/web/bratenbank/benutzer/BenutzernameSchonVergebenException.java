package de.hsrm.mi.web.bratenbank.benutzer;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ResourceBundle;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BenutzernameSchonVergebenException extends RuntimeException {
    public BenutzernameSchonVergebenException(String username) {
        super(String.format(ResourceBundle.getBundle("messages", LocaleContextHolder.getLocale()).getString("username.taken"), username));
    }

}
