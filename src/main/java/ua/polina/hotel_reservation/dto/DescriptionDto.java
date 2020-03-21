package ua.polina.hotel_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.hotel_reservation.entity.RoomType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DescriptionDto {
    private RoomType roomType;
    private int countOfBeds;
    private int countOfPersons;
    private BigDecimal costPerNight;
}
