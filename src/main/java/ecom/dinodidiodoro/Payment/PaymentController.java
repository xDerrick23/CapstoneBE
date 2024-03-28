package ecom.dinodidiodoro.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment paymentRequest) {
        return paymentService.createPayment(paymentRequest.getUser(), paymentRequest.getMerchs(), paymentRequest.getTotal());
    }
    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable UUID paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

}
