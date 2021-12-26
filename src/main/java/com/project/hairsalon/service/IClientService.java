package com.project.hairsalon.service;

import com.project.hairsalon.model.Category;
import com.project.hairsalon.model.Client;

import java.util.List;

public interface IClientService {
    List<Client> findAll();
    Client findByid(Long id);
    void save(Client client);
    void deleteById(Long id);
    Client findClientByPhone(String phone);
    Client findClientByPhoneAndPassword(String phone, String password);
}
