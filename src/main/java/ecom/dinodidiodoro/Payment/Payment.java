package ecom.dinodidiodoro.Payment;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ecom.dinodidiodoro.Merch.Merch;
import ecom.dinodidiodoro.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="payments")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "payment", cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Merch> merchs;
    private Double total;

    public Payment(User user, List<Merch> merchs, Double total) {
        this.user = user;
        this.merchs = merchs;
        this.total = total;
    }
}
