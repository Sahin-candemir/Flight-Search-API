package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.dto.SearchFlightResponse;
import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final FlightRepository flightRepository;
    public List<SearchFlightResponse> searchFlights(String from, String to, LocalDate departure, LocalDate arrival) {


        if(arrival==null){
            List<Flight> flights = flightRepository.findFlightsOnlyDeparture(from, to, departure);
            return flights.stream().map(this::mapToSearchFlightResponse).collect(Collectors.toList());
        }

            List<Flight> flights = flightRepository.findFlightsOnlyDeparture(from, to, departure);
            List<SearchFlightResponse> response1= flights.stream().map(this::mapToSearchFlightResponse).toList();

                List<Flight> flightsReturn = flightRepository.findFlightsOnlyArrival(from, to, arrival);
        List<SearchFlightResponse> response2= flightsReturn.stream().map(this::mapToSearchFlightResponseForRetur).toList();

        return Stream.concat(response1.stream(),response2.stream()).collect(Collectors.toList());
    }

    private SearchFlightResponse mapToSearchFlightResponse(Flight flight) {
        return SearchFlightResponse.builder()
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureTime(flight.getDepartureTime())
                .price(flight.getPrice())
                .build();
    }
    private SearchFlightResponse mapToSearchFlightResponseForRetur(Flight flight) {
        return SearchFlightResponse.builder()
                .departureAirport(flight.getArrivalAirport())
                .arrivalAirport(flight.getDepartureAirport())
                .departureTime(flight.getArrivalTime())
                .price(flight.getPrice())
                .build();
    }

}
