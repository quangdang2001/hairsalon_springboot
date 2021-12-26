package com.project.hairsalon.service.iplm;


import com.project.hairsalon.model.BillDetail;
import com.project.hairsalon.repo.BillDetailRepo;
import com.project.hairsalon.repo.ServicesRepo;
import com.project.hairsalon.service.IBillDetailService;
import com.project.hairsalon.service.IServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BillDetailService implements IBillDetailService {

    private final BillDetailRepo billDetailRepo;
    @Autowired
    private final IServicesService servicesService;

    @Override
    public List<BillDetail> findAll() {
        return billDetailRepo.findAll();
    }

    @Override
    public BillDetail findByid(Long id) {
        Optional<BillDetail> result = billDetailRepo.findById(id);
        BillDetail billDetail = null;
        if (result.isPresent()) {
            billDetail=result.get();
        }
        else {
            return null;
        }
        return billDetail;
    }

    @Override
    public void save(BillDetail billDetail) {
        billDetailRepo.save(billDetail);
    }

    @Override
    public void deleteById(Long id) {
        billDetailRepo.deleteById(id);
    }

    @Override
    public Double totalCount(List<Long> services) {
        double total=0;
        for (Long service : services){
            total += servicesService.findByid(service).getPrice();
        }
        return total;
    }
}
