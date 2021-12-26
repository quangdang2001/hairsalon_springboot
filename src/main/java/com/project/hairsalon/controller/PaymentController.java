package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.Category;
import com.project.hairsalon.model.Payment;
import com.project.hairsalon.service.IPayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {
    @Autowired
    private IPayementService payementService;

    @GetMapping("/payments")
    public List<Payment> getAllAgency(){
        return payementService.findAll();
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id){
        Payment payment = payementService.findByid(id);

        return ResponseEntity.status(HttpStatus.OK).body(payment);

    }

    @PostMapping("/payments")
    public Payment addAgency(@RequestBody Payment payment){
        payment.setId(null);
        payementService.save(payment);
        return payment;
    }

    @PutMapping("/payments")
    public Payment updateAgency(@RequestBody Payment payment){
        payementService.save(payment);
        return payment;
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        payementService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted Category id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
}
