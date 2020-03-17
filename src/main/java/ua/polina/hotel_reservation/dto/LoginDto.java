package ua.polina.hotel_reservation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotBlank
    @Size(min=5, max=20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String password;
}
