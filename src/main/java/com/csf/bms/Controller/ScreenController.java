package com.csf.bms.Controller;

import com.csf.bms.Dto.ScreenDto;
import com.csf.bms.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    // Create Screen
    @PostMapping("create")
    public ResponseEntity<ScreenDto> createScreen(@RequestBody ScreenDto screenDto) {

        return new ResponseEntity<>(
                screenService.createScreen(screenDto),
                HttpStatus.CREATED
        );
    }

    // Get Screen By Id
    @GetMapping("/{id}")
    public ResponseEntity<ScreenDto> getScreenById(@PathVariable Long id) {

        return ResponseEntity.ok(
                screenService.getScreenById(id)
        );
    }

    // Get All Screens
    @GetMapping
    public ResponseEntity<List<ScreenDto>> getAllScreens() {

        return ResponseEntity.ok(
                screenService.getAllScreens()
        );
    }

    // Get Screens By Theater
    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ScreenDto>> getScreensByTheater(
            @PathVariable Long theaterId) {

        return ResponseEntity.ok(
                screenService.getScreensByTheater(theaterId)
        );
    }
}