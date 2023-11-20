package com.revature.services;

import com.revature.beans.Reservation;
import com.revature.dto.ReservationDto;
import com.revature.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class ReservationHandler {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationHandler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Add new Reservation:
    public Mono<ServerResponse> addReservation(ServerRequest request) {
        Mono<Reservation> reservationMono = request.bodyToMono(ReservationDto.class).map(ReservationDto::getReservation);
        return ServerResponse.status(HttpStatus.CREATED).body(reservationRepository.saveAll(reservationMono), ReservationDto.class);
    }

    // Find Reservation by PNR:
    public Mono<ServerResponse> findByPnr(ServerRequest request) {
        String pnr = request.pathVariable("pnr").toUpperCase();
        return reservationRepository.findById(pnr).map(ReservationDto::new)
                .flatMap(reservationDto -> ServerResponse.ok().body(Mono.just(reservationDto), ReservationDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Update Reservation:
    // Note: Will probably need to add individual methods for updating flights. Maybe even separate addFlight() and deleteFlight() methods.
    public Mono<ServerResponse> updateReservation(ServerRequest request) {
        Mono<Reservation> reservationToUpdateMono = reservationRepository.findById(request.pathVariable("pnr").toUpperCase());
        Mono<ReservationDto> updatedReservationDtoMono = request.bodyToMono(ReservationDto.class);
        return reservationToUpdateMono.zipWith(updatedReservationDtoMono, (reservation, updatedInfo) -> {
            if(!updatedInfo.getPassengers().isEmpty()) {
                reservation.setPassengers(updatedInfo.getPassengers());
            }
            if(!updatedInfo.getFlights().isEmpty()) {
                reservation.setFlights(updatedInfo.getFlights());
            }
            if(updatedInfo.getSeats() != 0) {
                reservation.setSeats(updatedInfo.getSeats());
            }

            return reservation;
        }).flatMap(reservation -> ServerResponse.ok().body(reservationRepository.saveAll(Mono.just(reservation)), ReservationDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Delete Reservation:
    public Mono<ServerResponse> deleteReservation(ServerRequest request) {
        Mono<Reservation> reservationMono = reservationRepository.findById(request.pathVariable("pnr").toUpperCase());
        return reservationMono.flatMap(reservation -> ServerResponse.ok().build(reservationRepository.delete(reservation)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
