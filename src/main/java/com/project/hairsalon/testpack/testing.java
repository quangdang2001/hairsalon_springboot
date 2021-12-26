package com.project.hairsalon.testpack;

import com.project.hairsalon.model.Employee;
import com.project.hairsalon.service.IEmployeeService;
import com.project.hairsalon.service.IShiftService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class testing {
    @Autowired
    private IShiftService shiftService;
    @Autowired
    private IEmployeeService employeeService;
    @Test
    public void tesst(){
        Date date = new Date();
        date.setYear(2021);
        date.setMonth(10);
        date.setDate(1);
        System.out.println(shiftService.findShiftsByShiftDate(date));
    }
    @Test
    public void addAdminAcc(){
        Employee employee = new Employee(null,"admin","admin","admin","ROLE_ADMIN",null);
        employeeService.save(employee);
    }
}
