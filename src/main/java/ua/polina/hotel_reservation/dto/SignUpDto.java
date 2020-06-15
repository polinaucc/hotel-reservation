package ua.polina.hotel_reservation.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ua.polina.hotel_reservation.entity.FullAgeConstraint;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class SignUpDto {
    @NotBlank
    @Size(max = 40, message = "{email.size.error}")
    @Email(message = "{email.error}")
    private String email;

    @NotBlank
    @Size(min = 5, max = 20, message = "{username.error}")
    private String username;

    @NotBlank
    @Size(min = 5, max = 20, message = "password.error")
    private String password;

    @NotBlank
    @Size(min = 4, max = 20, message = "{first.name.error}")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{3,20}$", message = "{ukr.error}")
    private String firstNameUk;

    @NotBlank
    @Size(min = 5, max = 20, message = "{middle.name.error}")
    private String middleName;

    @NotBlank
    @Pattern(regexp = "(^[А-ЩЇЮЯІ][а-щїюяіь']{2,})(ович$|івна$|ївна$)", message = "{middle.name.ukr.error}")
    private String middleNameUk;

    @NotBlank
    @Size(min = 5, max = 20, message = "{last.name.error}")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{4,20}$", message = "{ukr.error}")
    private String lastNameUk;

    @NotBlank
    @Pattern(regexp = "^[А-Я]{2}[0-9]{6}$", message = "{illegal.passport}")
    private String passport;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @FullAgeConstraint
    private LocalDate birthday;
}
