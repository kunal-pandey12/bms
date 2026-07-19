package com.csf.bms.Controller;

import com.csf.bms.Dto.BookingDto;
import com.csf.bms.Dto.BookingRequestDto;
import com.csf.bms.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // new booking
    @PostMapping("/Ticket")
    public ResponseEntity<BookingDto> createBooking(
            @Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return new ResponseEntity<>(
                bookingService.createBooking(bookingRequestDto),
                HttpStatus.CREATED);
    }

    // ID se booking dekh sakte hai
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                bookingService.getBookingById(id));
    }

    // Booking number se booking dekho
    @GetMapping("/number/{bookingNumber}")
    public ResponseEntity<BookingDto> getBookingByNumber(
            @PathVariable String bookingNumber) {
        return ResponseEntity.ok(
                bookingService.getBookingByNumber(bookingNumber));
    }

    // User ki saari bookings dekh sakte hai
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                bookingService.getBookingByUserId(userId));
    }

    // Booking cancel kr sakte hai
    @PutMapping("/cancel/{id}")
    public ResponseEntity<BookingDto> cancelBooking(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                bookingService.cancelBooking(id));
    }
}