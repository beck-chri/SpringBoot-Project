package de.hsrm.mi.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GuteAdresseValidator.class)
@Documented
public @interface GuteAdresse {
    String message() default "Keine gueltige Addresse";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
