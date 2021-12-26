package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Bill;
import com.project.hairsalon.model.BillDetail;
import com.project.hairsalon.repo.BillRepo;
import com.project.hairsalon.service.IBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BillService implements IBillService {
    private final BillRepo billRepo;
    @Override
    public List<Bill> findAll() {
        return billRepo.findAll();
    }

    @Override
    public Bill findByid(Long id) {
        Optional<Bill> result = billRepo.findById(id);
        Bill bill = null;
        if (result.isPresent()) {
            bill=result.get();
        }
        else {
            return null;
        }
        return bill;
    }

    @Override
    public void save(Bill bill) {
        billRepo.save(bill);
    }

    @Override
    public void deleteById(Long id) {
        billRepo.deleteById(id);
    }

    @Override
    public List<Bill> findBillsByEmployee_Phone(String phone) {
        return billRepo.findBillsByEmployee_Phone(phone);
    }

    @Override
    public List<Bill> findBillsByClient_Phone(String phone) {
        return billRepo.findBillsByClient_Phone(phone);
    }

    @Override
    public Bill findBillsByPaypalId(String paypalId) {
        return billRepo.findBillsByPaypalId(paypalId);
    }
}
