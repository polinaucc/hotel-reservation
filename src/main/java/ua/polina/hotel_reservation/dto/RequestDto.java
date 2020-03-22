package ua.polina.hotel_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate checkOutDate;
}
