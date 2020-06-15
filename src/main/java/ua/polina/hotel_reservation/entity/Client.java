package ua.polina.hotel_reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "first_name_uk")
    private String firstNameUk;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "middle_name_uk")
    private String middleNameUk;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "last_name_uk", nullable = false)
    private String lastNameUk;

    @Column(name = "passport", unique = true, nullable = false)
    private String passport;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToMany(mappedBy = "client")
    private Set<Request> requests;

    public String enName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public String ukrName() {
        return firstNameUk + " " + middleNameUk + " " + lastNameUk;
    }
}
