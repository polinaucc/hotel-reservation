package ua.polina.hotel_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.hotel_reservation.entity.Room;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long roomId;
}
