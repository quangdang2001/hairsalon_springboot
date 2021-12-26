package com.project.hairsalon.repo;

import com.project.hairsalon.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {
    Client findClientByPhone(String phone);
    Client findClientByPhoneAndPassword(String phone,String password);
}
