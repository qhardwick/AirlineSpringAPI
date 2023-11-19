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
                .POST("/airline/flights", request -> flightHandler.addFlight(request))

                // Find all Flights:
                .GET("/airline/flights", request -> flightHandler.findAll(request))

                // Find flight by airline, flight number, and origin:
                .GET("/airline/flights/{origin}/{airline}/{flightNumber}",
                        request -> flightHandler.findByAirlineAndOriginAndFlightNumber(request))

                // Find all Flights departing from an airport on a specific date:
                .GET("/airline/flights/{origin}/{date}", request -> flightHandler.findByOriginAndDepartureDate(request))

                // Find all Flights from an Origin to a Destination on a specific date:
                .GET("/airline/flights/{origin}/{destination}/{date}",
                        request -> flightHandler.findAllByOriginAndDestinationAndDepartureDate(request))

                // Update Flight:
                .PUT("/airline/flights/{origin}/{airline}/{flightNumber}", request -> flightHandler.updateFlight(request))

                // Delete Flight:
                .DELETE("/airline/flights/{origin}/{airline}/{flightNumber}", request -> flightHandler.deleteFlight(request))


                .build();
    }
}