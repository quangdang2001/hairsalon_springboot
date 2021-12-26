package com.project.hairsalon.DTO;

import com.project.hairsalon.model.Agency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String phone;

    private String password;

    private String role;

    private Long agencyId;

}
