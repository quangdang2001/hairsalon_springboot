package com.project.hairsalon.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
public class Employee_ShiftsID implements Serializable {
    private Long employeeId;
    private Long shiftId;
}
