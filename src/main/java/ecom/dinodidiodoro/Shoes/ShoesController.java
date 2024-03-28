package ecom.dinodidiodoro.Shoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shoes")
public class ShoesController {

    @Autowired
    private ShoesService shoesService;

    @PostMapping
    public ResponseEntity<Shoes> createShoes(@RequestBody Shoes shoes) {
        Shoes createdShoes = shoesService.createShoes(shoes);
        return new ResponseEntity<>(createdShoes, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shoes> getShoesById(@PathVariable UUID id) {
        return shoesService.getShoesById(id)
                .map(shoes -> new ResponseEntity<>(shoes, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Shoes>> getAllShoes() {
        List<Shoes> shoesList = shoesService.getAllShoes();
        return new ResponseEntity<>(shoesList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shoes> updateShoes(@PathVariable UUID id, @RequestBody Shoes updatedShoes) {
        Shoes updated = shoesService.updateShoes(id, updatedShoes);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoes(@PathVariable UUID id) {
        shoesService.deleteShoes(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}