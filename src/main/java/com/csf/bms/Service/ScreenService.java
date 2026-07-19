package com.csf.bms.Service;

import com.csf.bms.Dto.ScreenDto;
import com.csf.bms.Dto.TheaterDto;
import com.csf.bms.Model.Screen;
import com.csf.bms.Model.Theater;
import com.csf.bms.Repo.ScreenRepo;
import com.csf.bms.Repo.TheaterRepo;
import com.csf.bms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepo screenRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    // Create Screen
    public ScreenDto createScreen(ScreenDto screenDto) {

        Theater theater = theaterRepo.findById(screenDto.getTheater().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Theater Not Found"));

        Screen screen = new Screen();
        screen.setName(screenDto.getName());
        screen.setTotalSeats(screenDto.getTotalSeats());
        screen.setTheater(theater);

        Screen savedScreen = screenRepo.save(screen);

        return mapToDto(savedScreen);
    }

    // Get Screen By Id
    public ScreenDto getScreenById(Long id) {

        Screen screen = screenRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Screen Not Found"));

        return mapToDto(screen);
    }

    // Get All Screens
    public List<ScreenDto> getAllScreens() {

        return screenRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get Screens By Theater
    public List<ScreenDto> getScreensByTheater(Long theaterId) {

        return screenRepo.findByTheaterId(theaterId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ScreenDto mapToDto(Screen screen) {

        TheaterDto theaterDto = new TheaterDto(
                screen.getTheater().getId(),
                screen.getTheater().getName(),
                screen.getTheater().getAddress(),
                screen.getTheater().getCity(),
                screen.getTheater().getTotalScreen()
        );

        return new ScreenDto(
                screen.getId(),
                screen.getName(),
                screen.getTotalSeats(),
                theaterDto
        );
    }
}