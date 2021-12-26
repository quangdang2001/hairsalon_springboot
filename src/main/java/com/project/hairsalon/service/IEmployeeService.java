package com.project.hairsalon.service;

import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findByid(Long id);
    void save(Employee employee);
    void deleteById(Long id);
    Employee findEmployeeByPhone(String phone);
    Employee findEmployeeByPhoneAndPassword(String phone, String password);
}
