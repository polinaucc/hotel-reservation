package ua.polina.hotel_reservation.entity;


import ua.polina.hotel_reservation.service.FullAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FullAgeValidator.class)
public @interface FullAgeConstraint {
    String message() default "{full.age}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
