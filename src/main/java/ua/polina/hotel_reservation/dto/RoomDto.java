package ua.polina.hotel_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.hotel_reservation.entity.Description;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private String roomNumber;
    private Long descriptionId;
}
