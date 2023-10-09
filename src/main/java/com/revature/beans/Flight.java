package com.revature.beans;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
public class Flight implements Serializable{

	private String airline;
	private int flightNumber;
	private String origin;
	private String destination;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private int businessCapacity;
	private int mainCapacity;
	private int miles;
	private SortedSet<String> availableBusinessSeats;
	private SortedSet<String> availableMainSeats;
	private List<User> users;
	
	public Flight() {
		super();
		this.businessCapacity = 9;
		this.mainCapacity = 40;
		this.users = new ArrayList<>();
		
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
	
	public Flight(int flightNumber, String origin, String destination, LocalTime departureTime, LocalTime arrivalTime, int miles) {
		this();
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.miles = miles;
	}
}