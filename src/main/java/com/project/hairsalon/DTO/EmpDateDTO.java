package com.project.hairsalon.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.hairsalon.model.Employee;
import lombok.Data;


import java.util.Date;
import java.util.List;
@Data
public class EmpDateDTO {
    private Employee employee;
    @JsonFormat(pattern="yyyy-MM-dd")
    private List<Date> dates;
}
