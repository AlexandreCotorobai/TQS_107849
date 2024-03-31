package tqs.hw1.bustickets.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    private int id;
    @NotNull
    private String origin;
    @NotNull
    private String destination;
    @NotNull
    private String departureDateTime;
    @NotNull
    private String arrivalDateTime;
    @NotNull
    private String company;
    @NotNull
    private double price;
    @NotNull
    private String currency;
    @NotNull
    private int availableSeats;
}
