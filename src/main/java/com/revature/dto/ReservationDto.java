package com.revature.dto;

import com.revature.beans.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    private List<String> flights;

    @Min(1)
    private int seats;

    public ReservationDto() {
        super();
        this.PNR = RandomStringUtils.randomAlphabetic(6).toUpperCase();
        this.passengers = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public ReservationDto(List<String> passengers, List<String> flights) {
        this();
        this.passengers = passengers;
        this.flights = flights;
    }

    public ReservationDto(Reservation reservation) {
        super();
        this.PNR = reservation.getPNR();
        this.passengers = reservation.getPassengers();
        this.flights = reservation.getFlights();
        this.seats = reservation.getSeats();
    }

    public Reservation getReservation() {
        return new Reservation(this.PNR, this.passengers, this.flights, this.getSeats());
    }

    public int getSeats() {
        return this.passengers.size();
    }
}
