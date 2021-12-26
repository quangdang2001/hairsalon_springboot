package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.EmployeeDTO;
import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.Agency;
import com.project.hairsalon.model.Employee;
import com.project.hairsalon.model.Payment;
import com.project.hairsalon.service.IAgencyService;
import com.project.hairsalon.service.IEmployeeService;
import com.project.hairsalon.service.IShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IAgencyService agencyService;
    @Autowired
    private IShiftService shiftService;
    @GetMapping("/employees")
    public List<Employee> getAllAgency(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id){
        Employee employee = employeeService.findByid(id);

        return ResponseEntity.status(HttpStatus.OK).body(employee);

    }

    @PostMapping("/employees")
    public Employee addAgency(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPassword(employeeDTO.getPassword());
        employee.setPhone(employeeDTO.getPhone());
        employee.setRole(employeeDTO.getRole());
        employee.setId(null);

        Agency agency = agencyService.findByid(employeeDTO.getAgencyId());
        employee.setAgency(agency);
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateAgency(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPassword(employeeDTO.getPassword());
        employee.setPhone(employeeDTO.getPhone());
        employee.setRole(employeeDTO.getRole());
        employee.setId(employeeDTO.getId());
        Agency agency = agencyService.findByid(employeeDTO.getAgencyId());
        employee.setAgency(agency);
        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){

        shiftService.deleteEmpShiftByEmpId(id);
        employeeService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted Employee id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
}
