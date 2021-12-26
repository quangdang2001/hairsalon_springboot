package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.Employee;
import com.project.hairsalon.repo.EmployeeRepo;
import com.project.hairsalon.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee findByid(Long id) {
        Optional<Employee> result = employeeRepo.findById(id);
        Employee employee = null;
        if (result.isPresent()) {
            employee=result.get();
        }
        else {
            return null;
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepo.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee findEmployeeByPhone(String phone) {
        return employeeRepo.findEmployeeByPhone(phone);
    }

    @Override
    public Employee findEmployeeByPhoneAndPassword(String phone, String password) {
        return employeeRepo.findEmployeeByPhoneAndPassword(phone,password);
    }
}
