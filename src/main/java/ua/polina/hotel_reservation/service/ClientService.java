package ua.polina.hotel_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.SignUpDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.Role;
import ua.polina.hotel_reservation.entity.User;
import ua.polina.hotel_reservation.repository.ClientRepository;
import ua.polina.hotel_reservation.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Service
public class ClientService {
    ClientRepository clientRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Client saveNewClient(SignUpDto signUpDto) {
        User user = new User();

        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.CLIENT);
        user.setAuthorities(roles);
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        userRepository.save(user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Client client = Client.builder()
                .firstName(signUpDto.getFirstName())
                .middleName(signUpDto.getMiddleName())
                .lastName(signUpDto.getLastName())
                .passport(signUpDto.getPassport())
                .birthday(LocalDate.parse(signUpDto.getBirthday(), formatter))
                .user(user)
                .build();

        return clientRepository.save(client);

    }
}