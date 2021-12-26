package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.UserDTO;
import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.Employee;
import com.project.hairsalon.service.iplm.ClientService;
import com.project.hairsalon.service.iplm.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody Client client){
        System.out.println(client.toString());
        if (clientService.findClientByPhone(client.getPhone())!=null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Existed phone");
        }
        clientService.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
}
