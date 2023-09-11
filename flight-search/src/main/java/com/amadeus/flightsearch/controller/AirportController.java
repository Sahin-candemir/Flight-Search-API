package com.amadeus.flightsearch.controller;

import com.amadeus.flightsearch.dto.AirportDto;
import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.UpdateFlightDto;
import com.amadeus.flightsearch.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;
    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<String> createAirport(@RequestBody AirportDto airportDto){
        airportService.createAirport(airportDto);
        return new ResponseEntity<>("Airport created successfully", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AirportDto>> getAllAirport(){
        return new ResponseEntity<>(airportService.getAllAirport(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable Long id){
        return new ResponseEntity<>(airportService.getAirportById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Secured("ADMIN")
    public ResponseEntity<String> updateAirportById(@PathVariable Long id, @RequestBody AirportDto airportDto){
        airportService.updateAirportById(id,airportDto);
        return new ResponseEntity<>("Airport updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Secured("ADMIN")
    public ResponseEntity<String> deleteAirportById(@PathVariable Long id){
        airportService.deleteAirportById(id);
        return new ResponseEntity<>("Airport deleted successfully", HttpStatus.OK);
    }
}
