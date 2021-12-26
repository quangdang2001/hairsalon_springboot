package com.project.hairsalon.DTO;

import com.project.hairsalon.model.Employee;
import com.project.hairsalon.model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class Employee_ShiftsDTO {
    private Employee employee;
    private List<HourDTO> hours;

}
