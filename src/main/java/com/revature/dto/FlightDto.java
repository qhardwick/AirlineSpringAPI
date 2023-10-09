package com.revature.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.revature.beans.Flight;

@Data
@Table("flights")
public class FlightDto {
	@PrimaryKeyColumn(
			name = "flightNumber",
			ordinal = 0,
			type = PrimaryKeyType.PARTITIONED
			)
	int flightNumber;
	
	@PrimaryKeyColumn(
			name = "origin",
			ordinal = 1,
			type = PrimaryKeyType.CLUSTERED
			)
	private String origin;
	
	@PrimaryKeyColumn(
			name = "destination",
			ordinal = 2,
			type = PrimaryKeyType.CLUSTERED
			)
	private String destination;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private int businessCapacity;
	private int mainCapacity;
	private int miles;
	private SortedSet<String> availableBusinessSeats;
	private SortedSet<String> availableMainSeats;
	private List<String> passengers;
}
