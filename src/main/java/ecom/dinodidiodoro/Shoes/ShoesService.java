package ecom.dinodidiodoro.Shoes;

import ecom.dinodidiodoro.Merch.Merch;
import ecom.dinodidiodoro.Merch.MerchRepository;
import ecom.dinodidiodoro.Merch.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoesService {

    @Autowired
    private ShoesRepository shoesRepository;
    @Autowired
    private MerchRepository merchRepository;
    @Autowired
    private MerchService merchService;
    public Shoes createShoes(Shoes shoes) {
        return shoesRepository.save(shoes);
    }

    public Optional<Shoes> getShoesById(UUID id) {
        return shoesRepository.findById(id);
    }


    public List<Shoes> getAllShoes() {
        return shoesRepository.findAll();
    }

    public Shoes updateShoes(UUID id, Shoes updatedShoes) {
        Optional<Shoes> existingShoesOptional = shoesRepository.findById(id);
        if (existingShoesOptional.isPresent()) {
            updatedShoes.setId(id);
            return shoesRepository.save(updatedShoes);
        } else {
            return null;
        }
    }

    public void deleteShoes(UUID id) {

        Optional<Shoes> optionalShoes = shoesRepository.findById(id);


        optionalShoes.ifPresent(shoes -> {

            List<Merch> merchToDelete = merchRepository. findByShoeAndVendutoFalse(shoes);
merchToDelete.forEach(merch -> {merchService.deleteMerch(merch.getId());});
            merchRepository.deleteAll(merchToDelete);

            shoesRepository.deleteById(id);
        });
    }

}