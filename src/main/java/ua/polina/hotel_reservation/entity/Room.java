package ua.polina.hotel_reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false)
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private Description description;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id) &&
                roomNumber.equals(room.roomNumber) &&
                description.equals(room.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, description);
    }
}
