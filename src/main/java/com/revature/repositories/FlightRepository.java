package com.revature.repositories;

import com.revature.beans.Flight;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FlightRepository extends ReactiveCassandraRepository<Flight, UUID> {

    // Find by Origin and Destination:
    // Note: ALLOW FILTERING is generally considered bad practice, so we may need to make origin and/or destination a clustering key:
    // Note: Neither the @AllowFiltering annotation nor adding it to the query seems to work, so we're just going to filter the results in the service for now:
    @AllowFiltering
    @Query("SELECT * FROM flights WHERE origin = ?0 AND destination = ?1")
    Flux<Flight> findAllByOriginAndDestination(String origin, String destination);

}
