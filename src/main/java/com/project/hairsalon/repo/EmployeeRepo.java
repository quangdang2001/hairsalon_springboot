package com.project.hairsalon.repo;

import com.project.hairsalon.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee findEmployeeByPhone(String phone);
    Employee findEmployeeByPhoneAndPassword(String phone, String password);
}
