package ua.polina.hotel_reservation.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class FullAgeValidatorTest {
    @Mock
    FullAgeValidator fullAgeValidator;
    @Mock
    ConstraintValidatorContext context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fullAgeValidator = new FullAgeValidator();
    }

    @Test
    public void isValid() {
        boolean res = fullAgeValidator.isValid(LocalDate.of(1997, 10, 25), context);
        assertTrue(res);
    }
}