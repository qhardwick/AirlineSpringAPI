package com.revature.controllers;

import com.revature.services.FlightHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

                // Find flight by UUID:
                .GET("/airline/flights/{id}", request -> flightHandler.findById(request))

                // Find all Flights by Origin and Destination cities:
                // TODO: Add functionality to find connecting flights:
                .GET("/airline/flights/{origin}/{destination}", request -> flightHandler.findByOriginAndDestination(request))

                // Update Flight:
                .PUT("/airline/flights/{id}", request -> flightHandler.updateFlight(request))

                // Delete Flight:
                .DELETE("/airline/flights/{id}", request -> flightHandler.deleteFlight(request))

                .build();
    }
}