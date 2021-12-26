package com.project.hairsalon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Employee_ShiftsID.class)
public class Employee_Shifts {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employeeId;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Shift shiftId;

    private boolean isWork;
}
