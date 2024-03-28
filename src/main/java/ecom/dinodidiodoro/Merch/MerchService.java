package ecom.dinodidiodoro.Merch;

import ecom.dinodidiodoro.Shoes.Shoes;
import ecom.dinodidiodoro.Shoes.ShoesRepository;
import ecom.dinodidiodoro.User.UserRepository;
import ecom.dinodidiodoro.User.UsersService;
import ecom.dinodidiodoro.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchService {
    private final MerchRepository merchRepository;
    private final UserRepository userRepository;
    private final UsersService usersService;
    private final ShoesRepository shoesRepository;

    public MerchService(MerchRepository merchRepository, UserRepository userRepository, ShoesRepository shoesRepository,UsersService usersService) {
        this.merchRepository = merchRepository;
        this.userRepository = userRepository;
        this.shoesRepository = shoesRepository;
        this.usersService = usersService;
    }
    public Merch createMerch(String nome, Size size, Double prezzo, UUID shoeId) {
        Merch merch = new Merch(nome, size, prezzo);
        merch.setUser(usersService.getCurrentUser());
        merch.setShoe(shoesRepository.findById(shoeId).orElseThrow(() -> new NotFoundException("non trovato")));
        return merchRepository.save(merch);
    }


    public List<Merch> getAllMerchs() {
        return merchRepository.findAll();
    }

    public Optional<Merch> getMerchById(UUID id) {
        return merchRepository.findById(id);
    }

    public void updateMerch(UUID id, String nome, Size size, Double prezzo) {
        Optional<Merch> optionalMerch = merchRepository.findById(id);
        optionalMerch.ifPresent(merch -> {
            merch.setNome(nome);
            merch.setSize(size);
            merch.setPrezzo(prezzo);
            merchRepository.save(merch);
        });
    }
    public List<Merch> getMerchandiseNotSold() {
        return merchRepository.findByVendutoFalse();
    }

    public void deleteMerch(UUID id) {
        Optional<Merch> optionalMerch = merchRepository.findById(id);
        optionalMerch.ifPresent(merch -> {

            if (merch.getUser() != null) {
                merch.getUser().getMerchs().remove(merch);
                userRepository.save(merch.getUser());
            }

            Shoes shoes = merch.getShoe();
            if (shoes != null) {
                shoes.getMerchs().remove(merch);
                shoesRepository.save(shoes);
            }

            merchRepository.deleteById(id);
        });
    }

}
