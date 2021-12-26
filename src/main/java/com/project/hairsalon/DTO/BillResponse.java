package com.project.hairsalon.DTO;

import com.project.hairsalon.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class BillResponse {
    private Long id;

    private Double price;

    private Client client;

    private Employee employee;

    private Payment payment;

    private Shift shift;

    private boolean status;

    private List<Services> services;
}
