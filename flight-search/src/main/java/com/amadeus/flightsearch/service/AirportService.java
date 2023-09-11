package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.dto.AirportDto;
import com.amadeus.flightsearch.exception.ResourceNotFoundException;
import com.amadeus.flightsearch.model.Airport;
import com.amadeus.flightsearch.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;
    public void createAirport(AirportDto airportDto) {
        Airport airport = modelMapper.map(airportDto, Airport.class);
        airportRepository.save(airport);
    }

    public List<AirportDto> getAllAirport() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream().map(airport -> modelMapper.map(airport, AirportDto.class)).collect(Collectors.toList());
    }

    public AirportDto getAirportById(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: "+id));
        return modelMapper.map(airport, AirportDto.class);
    }

    public void updateAirportById(Long id, AirportDto airportDto) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: "+id));
        airport.setCity(airportDto.getCity());
        airportRepository.save(airport);
    }

    public void deleteAirportById(Long id) {
        airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: "+id));
        airportRepository.deleteById(id);
    }
    public boolean isAirportExist(String city){
       return airportRepository.existsByCity(city);
    }
}
