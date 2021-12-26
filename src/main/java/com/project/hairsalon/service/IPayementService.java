package com.project.hairsalon.service;

import com.project.hairsalon.model.Employee;
import com.project.hairsalon.model.Payment;

import java.util.List;

public interface IPayementService {
    List<Payment> findAll();
    Payment findByid(Long id);
    void save(Payment payment);
    void deleteById(Long id);
}
