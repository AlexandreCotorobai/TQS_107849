package tqs.hw1.bustickets.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private int tripId;

    private String issueDateTime;

    private String name;
    
    private String email;

    private String phone;

    private int creditCardNumber;

    private int cvv;

    private String expirationDate;

    public Ticket(int tripId, String issueDateTime, String name, String email, String phone, int creditCardNumber, int cvv, String expirationDate) {
        this.tripId = tripId;
        this.issueDateTime = issueDateTime;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }
}
