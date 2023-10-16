package com.revature.controllers;

import com.revature.services.ReservationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration("reservationRouter")
public class ReservationRouter {

    private final ReservationHandler reservationHandler;

    @Autowired
    public ReservationRouter(ReservationHandler reservationHandler) {
        this.reservationHandler = reservationHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> reservationRouterFunction() {
        return RouterFunctions.route()

                // Add new Reservation:
                .POST("/reservations", request -> reservationHandler.addReservation(request))

                // Find all Reservations:
                .GET("/reservations", request -> reservationHandler.findAll(request))

                // Find Reservation by PNR:
                .GET("/reservations/{pnr}", request -> reservationHandler.findByPnr(request))

                // Update Reservation:
                .PUT("/reservations/{pnr}", request -> reservationHandler.updateReservation(request))

                // Delete Reservation:
                .DELETE("/reservations/{pnr}", request -> reservationHandler.deleteReservation(request))

                .build();
    }
}
