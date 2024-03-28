package ecom.dinodidiodoro.Merch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/merchs")
public class MerchController {

    private final MerchService merchService;

    @Autowired
    public MerchController(MerchService merchService) {
        this.merchService = merchService;
    }

    @PostMapping
    public ResponseEntity<Merch> createMerch(@RequestBody String nome, Size size, Double prezzo, UUID shoeId) {
        Merch merch = merchService.createMerch(nome, size, prezzo, shoeId);
        return new ResponseEntity<>(merch, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Merch>> getAllMerchs() {
        List<Merch> merchs = merchService.getAllMerchs();
        return new ResponseEntity<>(merchs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merch> getMerchById(@PathVariable UUID id) {
        Optional<Merch> optionalMerch = merchService.getMerchById(id);
        return optionalMerch.map(merch -> new ResponseEntity<>(merch, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/not-sold")
    public ResponseEntity<List<Merch>> getMerchandiseNotSold() {
        List<Merch> notSoldMerchs = merchService.getMerchandiseNotSold();
        return new ResponseEntity<>(notSoldMerchs, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMerch(@PathVariable UUID id, @RequestBody Merch merchRequest) {
        merchService.updateMerch(id, merchRequest.getNome(), merchRequest.getSize(), merchRequest.getPrezzo());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerch(@PathVariable UUID id) {
        merchService.deleteMerch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
