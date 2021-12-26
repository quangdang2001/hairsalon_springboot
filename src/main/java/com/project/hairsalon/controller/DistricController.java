package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.District;
import com.project.hairsalon.service.IDistricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DistricController {
    @Autowired
    private IDistricService districService;

    @GetMapping("/districts")
    public List<District> getAllDistric(){
        return districService.findAll();
    }
    @GetMapping("/districts/{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable Long id){
        District district = districService.findByid(id);

        return ResponseEntity.status(HttpStatus.OK).body(district);
    }
    @PostMapping("/districts")
    public District addDistricts(@RequestBody District district){
        district.setId(null);
        districService.save(district);
        return district;
    }

    @PutMapping("/districts")
    public District updateDistrict(@RequestBody District district){
        districService.save(district);
        return district;
    }

    @DeleteMapping("/districts/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long id){
        districService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted district id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
}
