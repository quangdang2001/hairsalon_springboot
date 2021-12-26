package com.project.hairsalon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String address;

    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    private District district;

    @OneToMany(mappedBy = "agency",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Employee> employees;

}
