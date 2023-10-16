package com.revature.services;

import com.revature.beans.Reservation;
import com.revature.dto.ReservationDto;
import com.revature.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ServerResponse.ok().body(reservationRepository.saveAll(reservationMono), ReservationDto.class);
    }

    // Find all Reservations:
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(reservationRepository.findAll(), ReservationDto.class);
    }

    // Find Reservation by PNR:
    public Mono<ServerResponse> findByPnr(ServerRequest request) {
        String pnr = request.pathVariable("pnr");
        return ServerResponse.ok().body(reservationRepository.findById(pnr), ReservationDto.class);
    }

    // Update Reservation:
    public Mono<ServerResponse> updateReservation(ServerRequest request) {
        String pnr = request.pathVariable("pnr");
        Mono<Reservation> reservationMono = request.bodyToMono(ReservationDto.class).map(ReservationDto::getReservation);
        return ServerResponse.ok().body(reservationRepository.saveAll(reservationMono), ReservationDto.class);
    }

    // Delete Reservation:
    public Mono<ServerResponse> deleteReservation(ServerRequest request) {
        String pnr = request.pathVariable("pnr");
        return ServerResponse.ok().body(reservationRepository.deleteById(pnr), ReservationDto.class);
    }
}
