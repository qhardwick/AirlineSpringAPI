package com.revature.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Table("Reservation")
public class Reservation {

    @PrimaryKey
    private String PNR;
    private List<String> passengers;
    private List<Segment> itinerary;

    public Reservation() {
        super();
        this.passengers = new ArrayList<>();
        this.itinerary = new ArrayList<>();
    }

}