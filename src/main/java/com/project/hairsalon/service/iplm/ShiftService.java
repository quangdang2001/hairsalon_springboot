package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Employee_Shifts;
import com.project.hairsalon.model.Services;
import com.project.hairsalon.model.Shift;
import com.project.hairsalon.repo.Employee_ShiftRepo;
import com.project.hairsalon.repo.ShiftRepo;
import com.project.hairsalon.service.IShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShiftService implements IShiftService {

    private final ShiftRepo shiftRepo;
    private final Employee_ShiftRepo employee_shiftRepo;

    @Override
    public List<Shift> findAll() {
        return shiftRepo.findAll();
    }

    @Override
    public Shift findByid(Long id) {
        Optional<Shift> result = shiftRepo.findById(id);
        Shift shift = null;
        if (result.isPresent()) {
            shift=result.get();
        }
        else {
            return null;
        }
        return shift;
    }

    @Override
    public void save(Shift shift) {
        shiftRepo.save(shift);
    }

    @Override
    public void deleteById(Long id) {
        shiftRepo.deleteById(id);
    }

    @Override
    public List<Employee_Shifts> findEmployee_ShiftsByEmployeeId_IdAndShiftId_ShiftDate(Long employeeId, Date shiftDate) {
        return employee_shiftRepo.findEmployee_ShiftsByEmployeeId_IdAndShiftId_ShiftDate(employeeId,shiftDate);
    }

    @Override
    public List<Shift> findShiftsByShiftDate(Date shiftDate) {
        return shiftRepo.findShiftsByShiftDate(shiftDate);
    }

    @Override
    public void saveEmployee_Shifts(Employee_Shifts employee_shifts) {
        employee_shiftRepo.save(employee_shifts);
    }

    @Override
    public void saveAll(List<Shift> shiftList) {
        shiftRepo.saveAll(shiftList);
    }

    @Override
    public Employee_Shifts findEmployee_ShiftsByEmpIdAndShiftId(Long empId, Long shiftId) {
        return employee_shiftRepo.findEmployee_ShiftsByEmployeeId_IdAndShiftId_Id(empId,shiftId);
    }

    @Override
    public List<Employee_Shifts> findEmpShiftByEmpId(Long empId) {
        return employee_shiftRepo.findEmployee_ShiftsByEmployeeId_Id(empId);
    }

    @Override
    public void deleteEmpShiftByEmpId(Long empId) {
        employee_shiftRepo.deleteAllByEmployeeId_Id(empId);
    }

}
