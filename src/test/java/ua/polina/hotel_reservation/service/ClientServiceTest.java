package ua.polina.hotel_reservation.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.polina.hotel_reservation.dto.SignUpDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.Role;
import ua.polina.hotel_reservation.entity.User;
import ua.polina.hotel_reservation.repository.ClientRepository;
import ua.polina.hotel_reservation.repository.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    @Mock
    ClientRepository clientRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ClientService clientService;

    @Mock
    PasswordEncoder passwordEncoder;

    SignUpDto signUpDto;
    User user;
    Client client;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        signUpDto = new SignUpDto();
        signUpDto.setEmail("testUser@ukr.net");
        signUpDto.setUsername("testUser");
        signUpDto.setPassword("testuser");
        signUpDto.setFirstNameUk("Антон");
        signUpDto.setFirstName("Anton");
        signUpDto.setMiddleName("Volodymyrovych");
        signUpDto.setMiddleNameUk("Володимирович");
        signUpDto.setLastName("Povarov");
        signUpDto.setLastNameUk("Поваров");
        signUpDto.setPassport("АБ456894");
        signUpDto.setBirthday(LocalDate.of(1999, 4, 18));

        user = new User();
        user.setId(1L);
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.CLIENT);
        user.setAuthorities(roles);
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());

        client = new Client();
        client.setId(1L);
        client.setFirstNameUk("Антон");
        client.setFirstName("Anton");
        client.setMiddleName("Volodymyrovych");
        client.setMiddleNameUk("Володимирович");
        client.setLastName("Povarov");
        client.setLastNameUk("Поваров");
        client.setPassport("АБ456894");
        client.setBirthday(LocalDate.of(1999, 4, 18));
        client.setUser(user);

    }

    @Test
    public void saveNewClient() {
        clientService.saveNewClient(signUpDto);
        verify(clientRepository, times(1)).save(any(Client.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getClientByUser() {
        Optional<Client> expectedClient = Optional.of(client);
        when(clientRepository.findClientByUser(user)).thenReturn(expectedClient);
        Optional<Client> actualClient = clientService.getClientByUser(user);
        Assert.assertEquals(expectedClient, actualClient);
    }
}