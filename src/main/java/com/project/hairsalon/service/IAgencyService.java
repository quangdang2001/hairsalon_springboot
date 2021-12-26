package com.project.hairsalon.service;

import com.project.hairsalon.model.Agency;


import java.util.List;
import java.util.Optional;

public interface IAgencyService {
    List<Agency> findAll();
    Agency findByid(Long id);
    void save(Agency agency);
    void deleteById(Long id);
}
