package com.revature.controllers;

import com.revature.services.FlightHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration("flightRouter")
public class FlightRouter {

    private FlightHandler flightHandler;

    @Autowired
    public FlightRouter(FlightHandler flightHandler) {
        this.flightHandler = flightHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> flightRouterFunction() {
        return RouterFunctions.route()

                // Add new Flight:
                .POST("/flights", request -> flightHandler.addFlight(request))

                // Find all Flights:
                .GET("/flights", request -> flightHandler.findAll(request))

                // Find flight by airline, flight number, and origin:
                .GET("/flights/{origin}/{airline}/{flightNumber}",
                        request -> flightHandler.findByAirlineAndOriginAndFlightNumber(request))

                // Find all Flights departing from an airport on a specific date:
                .GET("/flights/{origin}/{date}", request -> flightHandler.findByOriginAndDepartureDate(request))

                // Find all Flights from an Origin to a Destination on a specific date:
                .GET("/flights/{origin}/{destination}/{date}",
                        request -> flightHandler.findAllByOriginAndDestinationAndDepartureDate(request))

                // Update Flight:
                .PUT("/flights/{origin}/{airline}/{flightNumber}", request -> flightHandler.updateFlight(request))

                // Delete Flight:
                .DELETE("/flights/{origin}/{airline}/{flightNumber}", request -> flightHandler.deleteFlight(request))


                .build();
    }
}