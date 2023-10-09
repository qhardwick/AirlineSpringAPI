package com.revature.beans;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Data
@Table("Reservation")
public class Reservation {

    @PrimaryKey
    private String PNR;
    private List<User> users;
    private List<Flight> itinerary;

}