package com.project.hairsalon.service;

import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.District;

import java.util.List;

public interface IDistricService {
    List<District> findAll();
    District findByid(Long id);
    void save(District district);
    void deleteById(Long id);
    District findByName(String name);
}
