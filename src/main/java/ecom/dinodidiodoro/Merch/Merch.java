package ecom.dinodidiodoro.Merch;

import ecom.dinodidiodoro.Payment.Payment;
import ecom.dinodidiodoro.Shoes.Shoes;
import ecom.dinodidiodoro.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Table(name="merchs")
@Data
@NoArgsConstructor
public class Merch {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "shoe_id")
    private Shoes shoe;
    private String nome;
    private Boolean venduto = false;
private Size size;
private Double prezzo;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
    @ManyToOne
    @JoinColumn(name = "payments_id")
private Payment payment;

    public Merch(String nome, Size size, Double prezzo) {
        this.nome = nome;
        this.size = size;
        this.prezzo = prezzo;
    }
}
