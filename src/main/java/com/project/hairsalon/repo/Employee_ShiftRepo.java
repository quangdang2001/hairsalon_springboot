package com.project.hairsalon.repo;

import com.project.hairsalon.model.Employee_Shifts;
import com.project.hairsalon.model.Employee_ShiftsID;
import com.project.hairsalon.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface Employee_ShiftRepo extends JpaRepository<Employee_Shifts, Employee_ShiftsID> {
    List<Employee_Shifts> findEmployee_ShiftsByEmployeeId_IdAndShiftId_ShiftDate(Long employeeId, Date shiftDate);
    Employee_Shifts findEmployee_ShiftsByEmployeeId_IdAndShiftId_Id(Long empId,Long shiftId);
    List<Employee_Shifts> findEmployee_ShiftsByEmployeeId_Id(Long employeeId);
    void deleteAllByEmployeeId_Id(Long employeeId);

}
