package de.hsrm.mi.web.bratservice;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ResourceBundle;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BratenNichtVorhandenException extends RuntimeException {
    public BratenNichtVorhandenException() {
        super(String.format(ResourceBundle.getBundle("messages", LocaleContextHolder.getLocale()).getString("roast.notFound")));
    }
}
