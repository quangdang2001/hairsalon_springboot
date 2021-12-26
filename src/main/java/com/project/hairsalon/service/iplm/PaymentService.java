package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Employee;
import com.project.hairsalon.model.Payment;
import com.project.hairsalon.repo.PaymentRepo;
import com.project.hairsalon.service.IPayementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService implements IPayementService {
    private final PaymentRepo paymentRepo;
    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment findByid(Long id) {
        Optional<Payment> result = paymentRepo.findById(id);
        Payment payment = null;
        if (result.isPresent()) {
            payment=result.get();
        }
        else {
            return null;
        }
        return payment;
    }

    @Override
    public void save(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepo.deleteById(id);
    }
}
