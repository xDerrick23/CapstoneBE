package ecom.dinodidiodoro.Shoes;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShoesRepository extends JpaRepository<Shoes, UUID> {

}