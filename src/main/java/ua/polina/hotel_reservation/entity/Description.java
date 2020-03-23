package ua.polina.hotel_reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "description")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "count_persons", nullable = false)
    private int countOfPersons;

    @Column(name = "count_beds", nullable = false)
    private int countOfBeds;

    @Column(name = "cost", nullable = false)
    private BigDecimal costPerNight;

    @OneToMany(mappedBy = "description")
    private Set<Request> requests;

    @OneToMany(mappedBy = "description")
    private Set<Room> rooms;

    @Override
    public String toString() {
        return roomType + "for " + countOfPersons + " with " + countOfBeds;
    }
}
