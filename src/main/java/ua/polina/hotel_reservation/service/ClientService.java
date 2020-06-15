package ua.polina.hotel_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.SignUpDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.Role;
import ua.polina.hotel_reservation.entity.User;
import ua.polina.hotel_reservation.exception.DataExistsException;
import ua.polina.hotel_reservation.repository.ClientRepository;
import ua.polina.hotel_reservation.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;

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
        User user = saveUser(signUpDto);

        if (clientRepository.existsClientByPassport(signUpDto.getPassport()))
            throw new DataExistsException("passport.exists");

        Client client = Client.builder()
                .firstName(signUpDto.getFirstName())
                .firstNameUk(signUpDto.getFirstNameUk())
                .middleName(signUpDto.getMiddleName())
                .middleNameUk(signUpDto.getMiddleNameUk())
                .lastName(signUpDto.getLastName())
                .lastNameUk(signUpDto.getLastNameUk())
                .passport(signUpDto.getPassport())
                .birthday(signUpDto.getBirthday())
                .user(user)
                .build();

        return clientRepository.save(client);
    }

    private User saveUser(SignUpDto signUpDto) {
        User user = new User();

        if (userRepository.existsUserByEmail(signUpDto.getEmail()))
            throw new DataExistsException("email.exists");
        if (userRepository.existsUserByUsername(signUpDto.getUsername()))
            throw new DataExistsException("username.exists");

        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.CLIENT);
        user.setAuthorities(roles);
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        return userRepository.save(user);
    }

    public Optional<Client> getClientByUser(User user) {
        return clientRepository.findClientByUser(user);
    }
}
