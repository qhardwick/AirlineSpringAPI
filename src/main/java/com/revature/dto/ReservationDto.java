package com.revature.dto;

import com.revature.beans.Reservation;
import com.revature.beans.Segment;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Valid
public class ReservationDto {

    @NotEmpty
    @Size(min = 6, max = 6)
    private String PNR;

    @NotEmpty
    @Size(min = 1)
    private List<String> passengers;

    @NotEmpty
    @Size(min = 1)
    private List<Segment> itinerary;

    public ReservationDto() {
        super();
        this.passengers = new ArrayList<>();
        this.itinerary = new ArrayList<>();
    }

    public ReservationDto(Reservation reservation) {
        this();
        this.PNR = reservation.getPNR();
        this.passengers = reservation.getPassengers();
        this.itinerary = reservation.getItinerary();
    }

    public Reservation getReservation() {
        return new Reservation(this.PNR, this.passengers, this.itinerary);
    }
}
