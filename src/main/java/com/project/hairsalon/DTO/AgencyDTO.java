package com.project.hairsalon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AgencyDTO {

    private Long id;

    private String name;

    private String address;


    private Long districtId;
}
