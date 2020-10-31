package de.hsrm.mi.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class GuteAdresseValidator implements ConstraintValidator<GuteAdresse, String> {
    String pattern;

    @Override
    public void initialize(GuteAdresse constraintAnnotation) {
        pattern = "(((\\p{L})|-)+( )*)* [0-9]+(\\w)*, [0-9]{5} (((\\p{L})|-)+( )*)*";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches(pattern, value);
    }
}
