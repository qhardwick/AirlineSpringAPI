package com.revature.beans;

import com.revature.constants.AircraftType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Data
@AllArgsConstructor
@Table("Flight")
public class Flight {

	private String airline;

	@PrimaryKeyColumn(name = "origin", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String origin;

	@PrimaryKeyColumn(name = "flight_number", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String flightNumber;

	private String destination;

	@Column("departure_time")
	private LocalTime departureTime;

	@Column("arrival_time")
	private LocalTime arrivalTime;

	@PrimaryKeyColumn(name = "departure_date", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private LocalDate departureDate;

	@Column("arrival_date")
	private LocalDate arrivalDate;

	@Column("estimated_departure_time")
	private LocalTime estimatedDepartureTime;

	@Column("estimated_arrival_time")
	private LocalTime estimatedArrivalTime;

	@Column("estimated_departure_date")
	private LocalDate estimatedDepartureDate;

	@Column("estimated_arrival_date")
	private LocalDate estimatedArrivalDate;

	private int miles;

	private boolean international;

	@Column("business_capacity")
	private int businessCapacity;

	@Column("main_capacity")
	private int mainCapacity;

	@Column("business_availability")
	private int businessAvailability;

	@Column("main_availability")
	private int mainCabinAvailability;

	@Column("business_seat_map")
	private Map<String, String> businessSeatMap;

	@Column("main_seat_map")
	private Map<String, String> mainCabinSeatMap;

	@Column("aircraft_type")
	private AircraftType aircraftType;

	public Flight() {
		super();
		this.businessSeatMap = new HashMap<>();
		this.mainCabinSeatMap = new HashMap<>();
	}
}