package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.UpdateFlightDto;
import com.amadeus.flightsearch.exception.ResourceNotFoundException;
import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportService airportService;

    private final ModelMapper modelMapper;
    public void createFlight(FlightDto flightDto) {
        if(!airportService.isAirportExist(flightDto.getDepartureAirport()) || !airportService.isAirportExist(flightDto.getArrivalAirport())){
            throw new ResourceNotFoundException("Departure airport or Arrival airport not exist");
        }

        Flight flight = modelMapper.map(flightDto, Flight.class);
        flightRepository.save(flight);
    }

    public List<FlightDto> getAllFlight() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(flight -> modelMapper.map(flight,FlightDto.class)).collect(Collectors.toList());
    }

    public FlightDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id:"+id));
        return modelMapper.map(flight,FlightDto.class);
    }

    public void updateFlightById(Long id, UpdateFlightDto updateFlightDto) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id:"+id));
        //log.info(updateFlightDto.getArrivalTime().toString());
        flight.setPrice(updateFlightDto.getPrice());
        flight.setArrivalTime(updateFlightDto.getArrivalTime());
        flight.setDepartureTime(updateFlightDto.getDepartureTime());

        flightRepository.save(flight);
    }

    public void deleteFlightById(Long id) {
        flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id:"+id));
        flightRepository.deleteById(id);
    }
}
