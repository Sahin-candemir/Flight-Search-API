package com.amadeus.flightsearch.controller;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.UpdateFlightDto;
import com.amadeus.flightsearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createFlight(@RequestBody FlightDto flightDto){
        flightService.createFlight(flightDto);
        return new ResponseEntity<>("Flight created successfully", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlight(){
        return new ResponseEntity<>(flightService.getAllFlight(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id){
        return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Secured("ADMIN")
    public ResponseEntity<String> updateFlightById(@PathVariable Long id, @RequestBody UpdateFlightDto updateFlightDto){
        flightService.updateFlightById(id,updateFlightDto);
        return new ResponseEntity<>("Flight updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Secured("ADMIN")
    public ResponseEntity<String> deleteFlightById(@PathVariable Long id){
        flightService.deleteFlightById(id);
        return new ResponseEntity<>("Flight deleted successfully", HttpStatus.OK);
    }
}
