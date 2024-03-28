package ecom.dinodidiodoro.Shoes;

import ecom.dinodidiodoro.Merch.Merch;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="merchs")
@Data
@NoArgsConstructor
public class Shoes {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String immagine;
    private Double prezzo;
    private String descrizione;
    @OneToMany(mappedBy = "shoe")
    private List<Merch> merchs = new ArrayList<>();
}
