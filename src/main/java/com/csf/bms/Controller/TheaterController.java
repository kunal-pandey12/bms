package com.csf.bms.Controller;

import com.csf.bms.Dto.TheaterDto;
import com.csf.bms.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    // Create Theater
    @PostMapping("/create-all")
    public ResponseEntity<List<TheaterDto>> createTheaters(
            @RequestBody List<TheaterDto> theaterDtos) {
        return new ResponseEntity<>(
                theaterService.createTheaters(theaterDtos),
                HttpStatus.CREATED
        );
    }

    // Get Theater By Id
    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(
                theaterService.getTheaterById(id)
        );
    }

    // Get All Theaters
    @GetMapping
    public ResponseEntity<List<TheaterDto>> getAllTheater() {
        return ResponseEntity.ok(
                theaterService.getAllTheater()
        );
    }

    // Get Theaters By City
    @GetMapping("/city/{city}")
    public ResponseEntity<List<TheaterDto>> getAllTheaterByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                theaterService.getAllTheaterByCity(city)
        );
    }

    // Update Theater By City
    @PutMapping("/city/{city}")
    public ResponseEntity<TheaterDto> updateTheaterByCity(
            @PathVariable String city,
            @RequestBody TheaterDto theaterDto) {

        return ResponseEntity.ok(
                theaterService.updateTheaterByCity(city, theaterDto)
        );
    }

    // Delete All Theaters By City
    @DeleteMapping("/city/{city}")
    public ResponseEntity<String> deleteAllTheaterByCity(
            @PathVariable String city) {

        theaterService.deleteAllTheaterByCity(city);

        return ResponseEntity.ok("Theater deleted successfully.");
    }
}