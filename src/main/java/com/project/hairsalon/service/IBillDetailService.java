package com.project.hairsalon.service;

import com.project.hairsalon.model.BillDetail;

import java.util.List;
import java.util.Optional;

public interface IBillDetailService {
    List<BillDetail> findAll();
    BillDetail findByid(Long id);
    void save(BillDetail billDetail);
    void deleteById(Long id);
    Double totalCount(List<Long> services);
}
