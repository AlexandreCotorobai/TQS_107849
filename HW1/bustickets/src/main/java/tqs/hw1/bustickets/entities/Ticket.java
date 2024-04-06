package tqs.hw1.bustickets.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int tripId;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String phone;

    @NotNull
    private int creditCardNumber;

    @NotNull
    private int cvv;

    @NotNull
    private String expirationDate;

    public Ticket(int tripId, String name, String email, String phone, int creditCardNumber, int cvv, String expirationDate) {
        this.tripId = tripId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }
}
