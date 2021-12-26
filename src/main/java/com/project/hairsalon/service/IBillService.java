package com.project.hairsalon.service;


import com.project.hairsalon.model.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> findAll();
    Bill findByid(Long id);
    void save(Bill bill);
    void deleteById(Long id);
    List<Bill> findBillsByEmployee_Phone(String phone);
    List<Bill> findBillsByClient_Phone(String phone);
    Bill findBillsByPaypalId(String paypalId);
}
