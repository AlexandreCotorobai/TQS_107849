package tqs.hw1.bustickets.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String origin;
    @NotNull
    private String destination;
    @NotNull
    private String departureDateTime;
    private String arrivalDateTime;
    private String company;
    private double price;
    private String currency;
    private int availableSeats;

    public Trip(String origin, String destination, String departureDateTime, String arrivalDateTime, String company, double price, String currency, int availableSeats) {
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.company = company;
        this.price = price;
        this.currency = currency;
        this.availableSeats = availableSeats;
    }
}
