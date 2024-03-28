package ecom.dinodidiodoro.Merch;

import ecom.dinodidiodoro.Shoes.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MerchRepository extends JpaRepository<Merch, UUID> {
    List<Merch> findByVendutoFalse();
    List<Merch> findByShoeAndVendutoFalse(Shoes shoes);
}