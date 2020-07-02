package ua.polina.hotel_reservation.service;

import ua.polina.hotel_reservation.entity.FullAgeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FullAgeValidator implements ConstraintValidator<FullAgeConstraint, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext constraintValidatorContext) {
        try {
            long age = ChronoUnit.YEARS.between(birthday, LocalDate.now());
            return age > 18;
        } catch (NullPointerException ex) {
            return false;
        }
    }
}
