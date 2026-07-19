package com.csf.bms.Service;

import com.csf.bms.Dto.SeatDto;
import com.csf.bms.Model.Screen;
import com.csf.bms.Model.Seat;
import com.csf.bms.Repo.ScreenRepo;
import com.csf.bms.Repo.SeatRepo;
import com.csf.bms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private ScreenRepo screenRepo;

    // Create Seat
    public SeatDto createSeat(Long screenId, SeatDto seatDto){

        Screen screen = screenRepo.findById(screenId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Screen Not Found"));

        Seat seat = new Seat();
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setSeatType(seatDto.getSeatType());
        seat.setBasePrice(seatDto.getBasePrice());
        seat.setScreen(screen);

        Seat savedSeat = seatRepo.save(seat);

        return mapToDto(savedSeat);
    }

    // Get Seat By Id
    public SeatDto getSeatById(Long id){

        Seat seat = seatRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seat Not Found"));

        return mapToDto(seat);
    }

    // Get All Seats
    public List<SeatDto> getAllSeats(){

        return seatRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get Seats By Screen
    public List<SeatDto> getSeatsByScreen(Long screenId){

        return seatRepo.findByScreen_Id(screenId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SeatDto mapToDto(Seat seat){

        return new SeatDto(
                seat.getId(),
                seat.getSeatNumber(),
                seat.getSeatType(),
                seat.getBasePrice()
        );
    }
}