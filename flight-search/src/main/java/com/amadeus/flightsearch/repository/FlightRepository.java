package com.amadeus.flightsearch.repository;

import com.amadeus.flightsearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight f WHERE ?1= f.departure_airport AND ?2= f.arrival_airport AND" +
            " ?3= DATE(f.departure_time) AND ?4= DATE(f.arrival_time)",nativeQuery = true)
    List<Flight> findFlights(String from, String to, LocalDate departure, LocalDate
            arrival);

    @Query(value = "SELECT * FROM flight f WHERE ?1= f.departure_airport AND ?2= f.arrival_airport AND" +
            " ?3= DATE(f.departure_time)",nativeQuery = true)
    List<Flight> findFlightsOnlyDeparture(String from, String to, LocalDate departure);

    @Query(value = "SELECT * FROM flight f WHERE ?1= f.departure_airport AND ?2= f.arrival_airport AND" +
            " ?3= DATE(f.arrival_time)",nativeQuery = true)
    List<Flight> findFlightsOnlyArrival(String from, String to, LocalDate arrival);
}
