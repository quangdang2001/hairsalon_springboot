package com.project.hairsalon.service;

import com.project.hairsalon.model.Payment;
import com.project.hairsalon.model.Services;

import java.util.List;

public interface IServicesService {
    List<Services> findAll();
    Services findByid(Long id);
    void save(Services services);
    void deleteById(Long id);
}
