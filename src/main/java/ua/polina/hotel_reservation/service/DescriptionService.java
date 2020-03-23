package ua.polina.hotel_reservation.service;

import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.DescriptionDto;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.RoomType;
import ua.polina.hotel_reservation.repository.DescriptionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionService {
    DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public Description saveNewDescription(DescriptionDto descriptionDto) {
        Description description = Description.builder()
                .roomType(descriptionDto.getRoomType())
                //.roomType(RoomType.valueOfDisplay(descriptionDto.getRoomType()))
                .countOfPersons(descriptionDto.getCountOfPersons())
                .countOfBeds((descriptionDto.getCountOfBeds()))
                .costPerNight(descriptionDto.getCostPerNight())
                .build();
        return descriptionRepository.save(description);
    }

    public Optional<Description> getDescriptionByParameters(RequestDto requestDto) {
        return descriptionRepository.findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
                requestDto.getRoomType(),
                //RoomType.valueOfDisplay(requestDto.getRoomType()),
                requestDto.getCountOfPerson(),
                requestDto.getCountOfBeds()
        );
    }

    public List<Description> getAllDescriptions(){
        return descriptionRepository.findAll();
    }

    public Optional<Description> getDescriptionById(Long id){
        return descriptionRepository.findById(id);
    }
}
