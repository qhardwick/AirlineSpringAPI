package com.revature.dto;

import java.time.LocalTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.constants.AircraftType;
import com.revature.beans.Flight;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Valid
public class FlightDto {

	private UUID id;

	@NotEmpty
	@Size(min = 2, max = 2)
	private String airline;

	@NotEmpty
	@Size(min = 3, max = 3)
	private String origin;

	@NotNull
	@Min(1)
	@Max(9999)
	int flightNumber;

	@NotEmpty
	@Size(min = 3, max = 3)
	private String destination;

	@NotEmpty
	@Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
	private LocalTime departureTime;

	@NotEmpty
	@Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
	private LocalTime arrivalTime;

	@Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
	private LocalTime estimatedDepartureTime;

	@Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
	private LocalTime estimatedArrivalTime;

	@Min(0)
	private int miles;

	private boolean international;

	@Min(0)
	private int businessCapacity;

	@Min(0)
	private int mainCapacity;


	private int businessAvailability;

	private int mainCabinAvailability;

	private Map<String, String> businessSeatMap;

	private Map<String, String> mainCabinSeatMap;

	@NotEmpty
	@Size(min = 3, max = 3)
	private AircraftType aircraftType;

	public FlightDto(String airline, String origin, int flightNumber, String destination,
			LocalTime departureTime, LocalTime arrivalTime, int miles, boolean international, int businessCapacity,
			int mainCapacity, AircraftType aircraftType) {
		super();
		this.id = UUID.randomUUID();
		this.airline = airline;
		this.origin = origin;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.estimatedDepartureTime = departureTime;
		this.estimatedArrivalTime = arrivalTime;
		this.miles = miles;
		this.international = international;
		this.businessCapacity = businessCapacity;
		this.mainCapacity = mainCapacity;
		this.businessAvailability = businessCapacity;
		this.mainCabinAvailability = mainCapacity;
		this.businessSeatMap = new HashMap<>();
		this.mainCabinSeatMap = new HashMap<>();
		this.aircraftType = aircraftType;
	}

	public FlightDto(Flight flight) {
		super();
		this.id = flight.getId();
		this.airline = flight.getAirline();
		this.origin = flight.getOrigin();
		this.flightNumber = flight.getFlightNumber();
		this.destination = flight.getDestination();
		this.departureTime = flight.getDepartureTime();
		this.arrivalTime = flight.getArrivalTime();
		this.estimatedDepartureTime = flight.getEstimatedDepartureTime();
		this.estimatedArrivalTime = flight.getEstimatedArrivalTime();
		this.miles = flight.getMiles();
		this.international = flight.isInternational();
		this.businessCapacity = flight.getBusinessCapacity();
		this.mainCapacity = flight.getMainCapacity();
		this.businessAvailability = flight.getBusinessAvailability();
		this.mainCabinAvailability = flight.getMainCabinAvailability();
		this.businessSeatMap = flight.getBusinessSeatMap();
		this.mainCabinSeatMap = flight.getMainCabinSeatMap();
		this.aircraftType = flight.getAircraftType();
	}

	@JsonIgnore
	public Flight getFlight() {
		Flight flight = new Flight();
		flight.setId(this.id);
		flight.setAirline(this.airline.toUpperCase());
		flight.setOrigin(this.origin.toUpperCase());
		flight.setFlightNumber(this.flightNumber);
		flight.setDestination(this.destination.toUpperCase());
		flight.setDepartureTime(this.departureTime);
		flight.setArrivalTime(this.arrivalTime);
		flight.setEstimatedDepartureTime(this.estimatedDepartureTime);
		flight.setEstimatedArrivalTime(this.estimatedArrivalTime);
		flight.setMiles(this.miles);
		flight.setInternational(this.international);
		flight.setBusinessCapacity(this.businessCapacity);
		flight.setMainCapacity(this.mainCapacity);
		flight.setBusinessAvailability(this.businessAvailability);
		flight.setMainCabinAvailability(this.mainCabinAvailability);
		flight.setBusinessSeatMap(this.businessSeatMap);
		flight.setMainCabinSeatMap(this.mainCabinSeatMap);
		flight.setAircraftType(this.aircraftType);
		return flight;
	}

	/*   // Will likely implement this in the addFlight() method in FlightHandler.java rather than as a constructor:
	public FlightDto() {
		super();
		this.businessCapacity = 9;
		this.mainCapacity = 40;
		this.passengers = new ArrayList<>();

		this.availableBusinessSeats = new TreeSet<>();
		char[] businessColumns = {'A', 'C', 'D'};
		for (int row = 1; row < 4; row++) {
			for (char letter : businessColumns) {
				this.availableBusinessSeats.add("" + row + letter);
			}
		}

		this.availableMainSeats = new TreeSet<>();
		char[] mainColumns = {'A', 'C', 'D', 'F'};
		for (int row = 1; row < 11; row++) {
			for (char letter : mainColumns) {
				this.availableMainSeats.add("" + row + letter);
			}
		}
	}
	*/
}