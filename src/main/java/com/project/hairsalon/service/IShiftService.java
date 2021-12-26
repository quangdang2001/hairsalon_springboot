package com.project.hairsalon.service;

import com.project.hairsalon.model.Employee_Shifts;
import com.project.hairsalon.model.Services;
import com.project.hairsalon.model.Shift;

import java.util.Date;
import java.util.List;

public interface IShiftService {
    List<Shift> findAll();
    Shift findByid(Long id);
    void save(Shift shift);
    void deleteById(Long id);
    List<Employee_Shifts> findEmployee_ShiftsByEmployeeId_IdAndShiftId_ShiftDate(Long employeeId, Date shiftDate);
    List<Shift> findShiftsByShiftDate(Date shiftDate);
    void saveEmployee_Shifts(Employee_Shifts employee_shifts);
    void saveAll(List<Shift> shiftList);
    Employee_Shifts findEmployee_ShiftsByEmpIdAndShiftId(Long empId, Long shiftId);
    List<Employee_Shifts> findEmpShiftByEmpId(Long empId);
    void deleteEmpShiftByEmpId(Long empId);
}
