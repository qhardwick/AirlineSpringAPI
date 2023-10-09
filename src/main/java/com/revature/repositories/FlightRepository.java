package com.revature.repositories;

import com.revature.beans.Flight;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FlightRepository extends ReactiveCassandraRepository<Flight, String>{
	Mono<Flight> findByFlightNumber(int flightNumber);
	@AllowFiltering
	Flux<Flight> findByOrigin(String origin);
	@AllowFiltering
	Flux<Flight> findByDestination(String destination);
}
