package ecom.dinodidiodoro.Payment;

import ecom.dinodidiodoro.Merch.Merch;
import ecom.dinodidiodoro.Merch.MerchRepository;
import ecom.dinodidiodoro.User.User;
import ecom.dinodidiodoro.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private MerchRepository merchRepository;
    @Autowired
    private UserRepository userRepository;

    public Payment createPayment(User user, List<Merch> merchs, Double total) {
        Payment payment = new Payment(user, merchs, total);
       paymentRepository.save(payment);
        for (Merch merch : merchs) {
            merch.setVenduto(true);
            merch.setPayment(payment);
        }
        merchRepository.saveAll(merchs);
        user.getPayments().add(payment);
      userRepository.save(user);
        return payment;
    }

    public Payment getPaymentById(UUID paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment with id " + paymentId + " not found"));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }


}
