package com.csf.bms.Controller;

import com.csf.bms.Dto.SeatDto;
import com.csf.bms.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // Create Seat
    @PostMapping("/screen/{screenId}")
    public ResponseEntity<SeatDto> createSeat(
            @PathVariable Long screenId,
            @RequestBody SeatDto seatDto){

        return new ResponseEntity<>(
                seatService.createSeat(screenId, seatDto),
                HttpStatus.CREATED
        );
    }

    // Get Seat By Id
    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable Long id){

        return ResponseEntity.ok(
                seatService.getSeatById(id)
        );
    }

    // Get All Seats
    @GetMapping("all")
    public ResponseEntity<List<SeatDto>> getAllSeats(){

        return ResponseEntity.ok(
                seatService.getAllSeats()
        );
    }

    // Get Seats By Screen
    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<SeatDto>> getSeatsByScreen(
            @PathVariable Long screenId){

        return ResponseEntity.ok(
                seatService.getSeatsByScreen(screenId)
        );
    }
}