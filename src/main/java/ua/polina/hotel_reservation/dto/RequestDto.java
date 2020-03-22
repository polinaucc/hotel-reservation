package ua.polina.hotel_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.hotel_reservation.entity.RoomType;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    private RoomType roomType;
    private int countOfPerson;
    private int countOfBeds;
    //TODO: check date if it is future
    private String checkInDate;
    private String checkOutDate;
}
