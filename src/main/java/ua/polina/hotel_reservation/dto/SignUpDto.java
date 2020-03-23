package ua.polina.hotel_reservation.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class SignUpDto {
    @NotBlank(message = "The field must not be blank")
    @Size(max = 40, message = "Max size is 40 characters")
    @Email(message = "Illegal email")
    private String email;

    @NotBlank(message = "The field must not be blank")
    @Size(min = 5, max = 20, message = "Username size must be in the range from 5 to 40 characters")
    private String username;

    @NotBlank(message = "The field must not be blank")
    @Size(min = 5, max = 20, message = "Password size must be in the range from 5 to 20 characters")
    private String password;

    @NotBlank(message = "The field must not be blank")
    @Size(min = 5, max = 20, message = "First name size must be in the range from 5 to 20 characters")
    private String firstName;

    @NotBlank(message = "The field must not be blank")
    @Size(min = 5, max = 20, message = "Middle name size must be in the range from 5 to 20 characters")
    private String middleName;

    @NotBlank(message = "The field must not be blank")
    @Size(min = 5, max = 20, message = "Last name size must be in the range from 5 to 20 characters")
    private String lastName;

    @NotBlank(message = "The field must not be blank")
    @Pattern(regexp = "^[А-Я]{2}[0-9]{6}$", message = "Illegal passport!")
    private String passport;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthday;
}
