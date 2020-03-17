package ua.polina.hotel_reservation.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignUpDto {
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotBlank
    @Size(min = 5, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String middleName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[А-Я]{2}[0-9]{6}$", message = "Illegal passport!")
    private String passport;

    private String birthday;
}
