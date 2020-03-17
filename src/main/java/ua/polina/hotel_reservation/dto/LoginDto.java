package ua.polina.hotel_reservation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotBlank(message = "The field must not be blank")
    @Size(min=5, max=20, message = "Username size must be in the range from 5 to 20 characters")
    private String username;

    @NotBlank(message = "The field must not be blank")
    @Size(min = 5, max = 20, message = "Password size must be in the range from 5 to 20 characters")
    private String password;
}
