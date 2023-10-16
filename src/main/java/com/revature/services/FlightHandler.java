package com.revature.services;

import com.revature.beans.Flight;
import com.revature.dto.FlightDto;
import com.revature.repositories.FlightRepository;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class FlightHandler {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightHandler(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Add new Flight:
    @Valid
    public Mono<ServerResponse> addFlight(ServerRequest request) {
        Mono<Flight> flightMono = request.bodyToMono(FlightDto.class).map(FlightDto::getFlight);
        return ServerResponse.ok().body(flightRepository.saveAll(flightMono), FlightDto.class);
    }


    // Find all Flights:
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(flightRepository.findAll(), FlightDto.class);
    }

    // Find flight by airline, flight number, and origin:
    public Mono<ServerResponse> findByAirlineAndOriginAndFlightNumber(ServerRequest request) {
        return ServerResponse.ok().body(getFlightFromPath(request), FlightDto.class);
    }

    // Find all Flights departing from an airport on a specific date:
    public Mono<ServerResponse> findByOriginAndDepartureDate(ServerRequest request) {
        String origin = request.pathVariable("origin");
        LocalDate departureDate = LocalDate.parse(request.pathVariable("departureDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return ServerResponse.ok().body(flightRepository.findAllByOriginAndDepartureDate(origin, departureDate), FlightDto.class);
    }

    // Find all Flights from an Origin to a Destination on a specific date:
    public Mono<ServerResponse> findAllByOriginAndDestinationAndDepartureDate(ServerRequest request) {
        String origin = request.pathVariable("origin");
        String destination = request.pathVariable("destination");
        LocalDate departureDate = LocalDate.parse(request.pathVariable("departureDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return ServerResponse.ok().body(flightRepository.findAllByOriginAndDestinationAndDepartureDate(origin, destination, departureDate), FlightDto.class);
    }

    // Update Flight:
    public Mono<ServerResponse> updateFlight(ServerRequest request) {
        Mono<Flight> flightMono = request.bodyToMono(FlightDto.class).map(FlightDto::getFlight);
        return ServerResponse.ok().body(flightRepository.saveAll(flightMono), FlightDto.class);
    }

    // Delete Flight:
    public Mono<ServerResponse> deleteFlight(ServerRequest request) {
        return ServerResponse.ok().body(getFlightFromPath(request).flatMap(flightRepository::delete), FlightDto.class);
    }

    // Utility method to retrieve a flight from the database due to using a composite key:
    private Mono<Flight> getFlightFromPath(ServerRequest request) {
        String airline = request.pathVariable("airline");
        String origin = request.pathVariable("origin");
        int flightNumber = Integer.parseInt(request.pathVariable("flightNumber"));
        return flightRepository.findByAirlineAndOriginAndFlightNumber(airline, origin, flightNumber);
    }
}
