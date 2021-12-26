package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.EmpDateDTO;
import com.project.hairsalon.DTO.Employee_ShiftsDTO;
import com.project.hairsalon.DTO.HourDTO;
import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.Agency;
import com.project.hairsalon.model.Employee;
import com.project.hairsalon.model.Employee_Shifts;
import com.project.hairsalon.model.Shift;
import com.project.hairsalon.service.IEmployeeService;
import com.project.hairsalon.service.IShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ShiftController {
    @Autowired
    private IShiftService shiftService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/shifts")
    public List<Shift> getAllShift(){
        return shiftService.findAll();
    }
    @GetMapping("/shifts/{id}")
    public ResponseEntity<?> getShiftById(@PathVariable Long id){

        List<Employee_Shifts> employee_shifts = shiftService.findEmpShiftByEmpId(id);
        Employee_ShiftsDTO employee_shiftsDTO = new Employee_ShiftsDTO();
        employee_shiftsDTO.setHours(new ArrayList<>());
        employee_shiftsDTO.setEmployee(employee_shifts.get(0).getEmployeeId());
        for (Employee_Shifts employee_shifts1 : employee_shifts){
            employee_shiftsDTO.getHours().add(new HourDTO(employee_shifts1.getShiftId(),employee_shifts1.isWork()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee_shiftsDTO);
    }

//    @PostMapping("/shifts")
//    public Shift addAgency(@RequestBody Shift shift){
//        shift.setId(null);
//        shiftService.save(shift);
//        return shift;
//    }
    @PutMapping("/shifts")
    public Shift updateAgency(@RequestBody Shift shift){
        shiftService.save(shift);
        return shift;
    }
    @DeleteMapping("/shifts/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        shiftService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted shift id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
    @GetMapping("/shift/check")
    public ResponseEntity<?> findEmployee_Shift(@RequestParam() Long employeeId, @RequestParam Date shiftDate){
        shiftDate.setHours(12);

        List<Employee_Shifts> employee_shifts = shiftService.findEmployee_ShiftsByEmployeeId_IdAndShiftId_ShiftDate(employeeId,shiftDate);
        if (employee_shifts == null || employee_shifts.size()==0){
            return ResponseEntity.status(HttpStatus.OK).body("Don't have shiftdate for emp");
        }

        Employee_ShiftsDTO employee_shiftsDTO = new Employee_ShiftsDTO();
        employee_shiftsDTO.setHours(new ArrayList<>());
        employee_shiftsDTO.setEmployee(employee_shifts.get(0).getEmployeeId());
        for (Employee_Shifts employee_shifts1 : employee_shifts){
            employee_shiftsDTO.getHours().add(new HourDTO(employee_shifts1.getShiftId(),employee_shifts1.isWork()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee_shiftsDTO);
    }
    @PostMapping("/shift/set-employee")
        public ResponseEntity<?> setEmployeeShift(@RequestParam Long employeeId,@RequestParam Date shiftDate){
        shiftDate.setHours(12);
        List<Shift> shiftList = setShiftForDate(shiftDate);

        Employee employee = employeeService.findByid(employeeId);
        if (employee== null){
            return ResponseEntity.status(HttpStatus.OK).body("Employee does not exist");
        }
        List<Shift> shiftListtemp;
        List<Employee_Shifts> tempEmployee_shifts = new ArrayList<>();
        Employee_Shifts employee_shifts = new Employee_Shifts();
        Date date = null;
        int count = 8;
        for (Shift shift : shiftList){

            if (date!= shift.getShiftDate()){
                date = shift.getShiftDate();
                tempEmployee_shifts = shiftService
                        .findEmployee_ShiftsByEmployeeId_IdAndShiftId_ShiftDate(employeeId,shift.getShiftDate());
                if (tempEmployee_shifts.isEmpty())
                    count =0;
            }
            if (count < 8) {
                shiftListtemp = shiftService.findShiftsByShiftDate(shift.getShiftDate());
                if (shiftListtemp.size() <= 8) {
                    shiftService.save(shift);
                }

                employee_shifts.setWork(true);
                employee_shifts.setEmployeeId(employee);
                employee_shifts.setShiftId(shift);
                shiftService.saveEmployee_Shifts(employee_shifts);
                count++;
            }
            tempEmployee_shifts.clear();
        }
        return ResponseEntity.ok().body("Set shift successful");

    }
    @GetMapping("/shift/emp-date/{id}")
    public ResponseEntity<?> getDateEmp(@PathVariable Long id){
        EmpDateDTO empDateDTO = new EmpDateDTO();
        empDateDTO.setDates(new ArrayList<>());
        List<Employee_Shifts> employee_shifts = shiftService.findEmpShiftByEmpId(id);
        empDateDTO.setEmployee(employee_shifts.get(0).getEmployeeId());
        for (Employee_Shifts employee_shifts1 : employee_shifts){
            if (!empDateDTO.getDates().contains(employee_shifts1.getShiftId().getShiftDate()))
            empDateDTO.getDates().add(employee_shifts1.getShiftId().getShiftDate());
        }
        return ResponseEntity.status(HttpStatus.OK).body(empDateDTO);
    }
    public List<Shift> setShiftForDate(Date date){

        List<Shift> shiftList = new ArrayList<>();
        for (int i=0;i<7;i++){

            shiftList.add(new Shift(null,"13h","13h30",date));
            shiftList.add(new Shift(null,"13h30","14h",date));
            shiftList.add(new Shift(null,"14h","14h30",date));
            shiftList.add(new Shift(null,"14h30","15h",date));
            shiftList.add(new Shift(null,"15h","15h30",date));
            shiftList.add(new Shift(null,"15h30","16h",date));
            shiftList.add(new Shift(null,"16h","16h30",date));
            shiftList.add(new Shift(null,"16h30","17h",date));
            date = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        }
        return shiftList;
    }
}
