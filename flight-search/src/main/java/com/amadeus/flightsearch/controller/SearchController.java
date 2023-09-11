package com.amadeus.flightsearch.controller;

import com.amadeus.flightsearch.dto.SearchFlightResponse;
import com.amadeus.flightsearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;


    //http://localhost:8080/api/search?from=ankara&to=istanbul&departure=2023-10-01&arrival=2023-10-03
    @GetMapping
    public ResponseEntity<List<SearchFlightResponse>> searchFlights(
            @RequestParam(name = "from",required = true) String from,
            @RequestParam(name = "to",required = true) String to,
            @RequestParam(name = "departure",required = true) LocalDate departure,
            @RequestParam(name = "return", required = false) LocalDate arrival){

        return new ResponseEntity<>(searchService.searchFlights(from, to, departure, arrival), HttpStatus.OK);
    }
}
