package com.project.hairsalon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Payment payment;

    @Column(nullable = false)
    private Boolean status;

    private String paypalId;

    @OneToMany(mappedBy = "bill",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<BillDetail> billDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private Shift shift;


}
