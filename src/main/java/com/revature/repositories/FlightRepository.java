package com.revature.repositories;

import com.revature.beans.Flight;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends ReactiveCassandraRepository<Flight, String> {

    // Find by Flight Number, Origin, and Date:
    @Query("SELECT * FROM flights WHERE flight_number = ?0 AND departure_date = ?1")
    Flux<Flight> findAllByFlightNumberAndDepartureDate(String flightNumber, LocalDate departureDate);

}
