package com.revature.beans;

import com.revature.constants.AircraftType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

@Data
@AllArgsConstructor
@Table
public class Flight {
	// Q: Is it better to filter results in the code rather than in the database?
	// A: It depends on the size of the data. If the data is small, it is better to filter in the code.
	// Q: What would be considered a small amount of data?
	// A: A small amount of data would be less than 1000 rows.
	@PrimaryKeyColumn(
			name = "airline",
			ordinal = 0,
			type = PrimaryKeyType.PARTITIONED)
	private String airline;

	@PrimaryKeyColumn(
			name = "origin",
			ordinal = 1,
			type = PrimaryKeyType.CLUSTERED)
	private String origin;

	@PrimaryKeyColumn(
			name = "flight_number",
			ordinal = 2,
			type = PrimaryKeyType.CLUSTERED)
	private int flightNumber;

	private String destination;

	@Column("departure_time")
	private LocalDateTime departureTime;

	@Column("arrival_time")
	private LocalDateTime arrivalTime;

	@Column("estimated_departure_time")
	private LocalDateTime estimatedDepartureTime;

	@Column("estimated_arrival_time")
	private LocalDateTime estimatedArrivalTime;

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

	public Flight(String airline, String origin, int flightNumber, String destination, LocalDateTime departureTime,
			LocalDateTime arrivalTime, int miles, AircraftType aircraftType) {
		super();
		this.airline = airline;
		this.origin = origin;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.estimatedDepartureTime = departureTime;
		this.estimatedArrivalTime = arrivalTime;
		this.miles = miles;
		this.aircraftType = aircraftType;
	}
}