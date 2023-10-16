package com.revature.beans;

import com.revature.constants.ConnectionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Segment {
    private int segmentNumber;
    private ConnectionType connectionType;
    private String airline;
    private int flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    public Segment() {
        super();
        this.connectionType = ConnectionType.ORIGIN;
    }
}
