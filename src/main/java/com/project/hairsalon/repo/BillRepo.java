package com.project.hairsalon.repo;

import com.project.hairsalon.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {
    List<Bill> findBillsByEmployee_Phone(String phone);
    List<Bill> findBillsByClient_Phone(String phone);
    Bill findBillsByPaypalId(String paypalId);
}
