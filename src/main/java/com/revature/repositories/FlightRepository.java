package com.revature.repositories;

import com.revature.beans.Flight;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends ReactiveCassandraRepository<Flight, String> {

    // Find by airline, origin, and flight number:
    @Query("SELECT * FROM flights WHERE airline = ?0 AND origin = ?1 AND flight_number = ?2")
    Mono<Flight> findByAirlineAndOriginAndFlightNumber(String airline, String origin, int flightNumber);

    // Find all flights by origin on a specific query date:
    @Query("SELECT * FROM flights WHERE origin = ?0 AND departure_time = ?1")
    Flux<Flight> findAllByOriginAndDepartureDate(String origin, LocalDate departureDate);

    // Find all flights by origin, destination, and departure date:
    @Query("SELECT * FROM flights WHERE origin = ?0 AND destination = ?1 AND departure_time = ?2")
    Flux<Flight> findAllByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDate departureDate);
}
