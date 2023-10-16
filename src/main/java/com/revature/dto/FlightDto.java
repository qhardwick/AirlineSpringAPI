package com.revature.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.revature.constants.AircraftType;
import com.revature.beans.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Valid
public class FlightDto {

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

	@NotNull
	@FutureOrPresent
	private LocalDateTime departureTime;

	@NotNull
	@FutureOrPresent
	private LocalDateTime arrivalTime;

	@NotNull
	@FutureOrPresent
	private LocalDateTime estimatedDepartureTime;

	@NotNull
	@FutureOrPresent
	private LocalDateTime estimatedArrivalTime;

	@Min(0)
	private int miles;

	private boolean international;

	@Min(0)
	private int businessCapacity;

	@Min(0)
	private int mainCapacity;


	private int businessAvailability;

	private int mainAvailability;

	private Map<String, String> businessSeatMap;

	private Map<String, String> mainCabinSeatMap;

	@NotEmpty
	@Size(min = 3, max = 3)
	private AircraftType aircraftType;

	public FlightDto() {
		super();
		this.businessSeatMap = new HashMap<>();
		this.mainCabinSeatMap = new HashMap<>();
	}

	public FlightDto(Flight flight) {
		this();
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
		this.mainAvailability= flight.getMainCabinAvailability();
		this.businessSeatMap = flight.getBusinessSeatMap();
		this.mainCabinSeatMap = flight.getMainCabinSeatMap();
		this.aircraftType = flight.getAircraftType();
	}

	public Flight getFlight() {
		Flight flight = new Flight();
		flight.setAirline(this.airline);
		flight.setOrigin(this.origin);
		flight.setFlightNumber(this.flightNumber);
		flight.setDestination(this.destination);
		flight.setDepartureTime(this.departureTime);
		flight.setArrivalTime(this.arrivalTime);
		flight.setEstimatedDepartureTime(this.estimatedDepartureTime);
		flight.setEstimatedArrivalTime(this.estimatedArrivalTime);
		flight.setMiles(this.miles);
		flight.setInternational(this.international);
		flight.setBusinessCapacity(this.businessCapacity);
		flight.setMainCapacity(this.mainCapacity);
		flight.setBusinessAvailability(this.businessAvailability);
		flight.setMainCabinAvailability(this.mainAvailability);
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