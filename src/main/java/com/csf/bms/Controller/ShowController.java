package com.csf.bms.Controller;

import com.csf.bms.Dto.ShowDto;
import com.csf.bms.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    // Create Show
    @PostMapping("/create")
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto showDto) {
        return new ResponseEntity<>(
                showService.createShow(showDto),
                HttpStatus.CREATED
        );
    }

    // Get Show By Id
    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    // Get All Shows
    @GetMapping("all")
    public ResponseEntity<List<ShowDto>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShow());
    }

    // Get Shows By Movie Id
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowDto>> getShowsByMovie(
            @PathVariable Long movieId) {

        return ResponseEntity.ok(
                showService.getAllShowsByMovie(movieId)
        );
    }

    // Get Shows By Movie And City
    @GetMapping("/movie/{movieId}/city/{city}")
    public ResponseEntity<List<ShowDto>> getShowsByMovieAndCity(
            @PathVariable Long movieId,
            @PathVariable String city) {

        return ResponseEntity.ok(
                showService.getShowsByMovieAndCity(movieId, city)
        );
    }

    // Get Shows Between Dates
    @GetMapping("/date-range")
    public ResponseEntity<List<ShowDto>> getShowsByDateRange(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate) {

        return ResponseEntity.ok(
                showService.getShowsByDateRange(startDate, endDate)
        );
    }
}