package com.revature.services;

import com.revature.beans.Flight;
import com.revature.dto.FlightDto;
import com.revature.dto.UserDto;
import com.revature.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FlightHandler {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightHandler(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Add new Flight:
    // TODO: Solve Validation issue:
    @Valid
    public Mono<ServerResponse> addFlight(ServerRequest request) {
        Mono<Flight> userMono = request.bodyToMono(FlightDto.class).map(FlightDto::getFlight);
        return ServerResponse.status(HttpStatus.CREATED).body(flightRepository.saveAll(userMono), UserDto.class);
    }

    // Find flight by UUID:
    public Mono<ServerResponse> findById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));
        return flightRepository.findById(id).map(FlightDto::new)
                .flatMap(flightDto -> ServerResponse.ok().body(Mono.just(flightDto), FlightDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Find all Flights between an Origin and Destination
    public Mono<ServerResponse> findByOriginAndDestination(ServerRequest request) {
        String origin = request.pathVariable("origin");
        String destination = request.pathVariable("destination");
        return ServerResponse.ok().body(flightRepository.findAll()
                        .filter(flight -> flight.getOrigin().equalsIgnoreCase(origin))
                        .filter(flight -> flight.getDestination().equalsIgnoreCase(destination)), FlightDto.class);
    }

    // Update Flight:
    // Note: Some of these fields would likely require the creation of a new Flight object, but we'll include them here for now:
    public Mono<ServerResponse> updateFlight(ServerRequest request) {
        Mono<Flight> flightToUpdateMono = flightRepository.findById(UUID.fromString(request.pathVariable("id")));
        Mono<FlightDto> updatedFlightMono = request.bodyToMono(FlightDto.class);
        return flightToUpdateMono.zipWith(updatedFlightMono, (flightToUpdate, updatedFlight) -> {
            if(updatedFlight.getAirline() != null) {
                flightToUpdate.setAirline(updatedFlight.getAirline());
            }
            if(updatedFlight.getOrigin() != null) {
                flightToUpdate.setOrigin(updatedFlight.getOrigin());
            }
            if(updatedFlight.getFlightNumber() != 0) {
                flightToUpdate.setFlightNumber(updatedFlight.getFlightNumber());
            }
            if(updatedFlight.getDestination() != null) {
                flightToUpdate.setDestination(updatedFlight.getDestination());
            }
            if(updatedFlight.getDepartureTime() != null) {
                flightToUpdate.setDepartureTime(updatedFlight.getDepartureTime());
            }
            if(updatedFlight.getArrivalTime() != null) {
                flightToUpdate.setArrivalTime(updatedFlight.getArrivalTime());
            }
            if(updatedFlight.getEstimatedDepartureTime() != null) {
                flightToUpdate.setEstimatedDepartureTime(updatedFlight.getEstimatedDepartureTime());
            }
            if(updatedFlight.getEstimatedArrivalTime() != null) {
                flightToUpdate.setEstimatedArrivalTime(updatedFlight.getEstimatedArrivalTime());
            }
            if(updatedFlight.getMiles() != 0) {
                flightToUpdate.setMiles(updatedFlight.getMiles());
            }
            if(updatedFlight.isInternational() != flightToUpdate.isInternational()) {
                flightToUpdate.setInternational(updatedFlight.isInternational());
            }
            if(updatedFlight.getBusinessCapacity() != 0) {
                flightToUpdate.setBusinessCapacity(updatedFlight.getBusinessCapacity());
            }
            if(updatedFlight.getMainCapacity() != 0) {
                flightToUpdate.setMainCapacity(updatedFlight.getMainCapacity());
            }
            if(updatedFlight.getBusinessAvailability() != 0) {
                flightToUpdate.setBusinessAvailability(updatedFlight.getBusinessAvailability());
            }
            if(updatedFlight.getMainCabinAvailability() != 0) {
                flightToUpdate.setMainCabinAvailability(updatedFlight.getMainCabinAvailability());
            }
            if(updatedFlight.getBusinessSeatMap() != null) {
                flightToUpdate.setBusinessSeatMap(updatedFlight.getBusinessSeatMap());
            }
            if(updatedFlight.getMainCabinSeatMap() != null) {
                flightToUpdate.setMainCabinSeatMap(updatedFlight.getMainCabinSeatMap());
            }
            if(updatedFlight.getAircraftType() != null) {
                flightToUpdate.setAircraftType(updatedFlight.getAircraftType());
            }

            return flightToUpdate;
        }).flatMap(flight -> ServerResponse.ok().body(flightRepository.save(flight), FlightDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Delete Flight:
    public Mono<ServerResponse> deleteFlight(ServerRequest request) {
        Mono<Flight> flightMono = flightRepository.findById(UUID.fromString(request.pathVariable("id")));
        return flightMono.flatMap(flight -> ServerResponse.noContent().build(flightRepository.delete(flight)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
