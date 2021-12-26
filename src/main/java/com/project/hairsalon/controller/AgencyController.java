package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.AgencyDTO;
import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.Agency;
import com.project.hairsalon.model.District;
import com.project.hairsalon.model.Services;
import com.project.hairsalon.service.IAgencyService;
import com.project.hairsalon.service.IDistricService;
import com.project.hairsalon.utils.AmazonUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AgencyController {
    @Autowired
    private IAgencyService agencyService;
    @Autowired
    private IDistricService districService;
    @Autowired
    private AmazonUploadService amazonUploadService;

    @GetMapping("/agencies")
    public List<Agency> getAllAgency(){
        return agencyService.findAll();
    }
    @GetMapping("/agencies/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id){

        Agency agency = agencyService.findByid(id);

        return ResponseEntity.status(HttpStatus.OK).body(agency);
    }

    @PostMapping("/agencies")
    public Agency addAgency(@RequestBody AgencyDTO agencyDTO){
        Agency agency = new Agency();
        agency.setId(null);
        agency.setName(agencyDTO.getName());
        agency.setAddress(agencyDTO.getAddress());
        District district = districService.findByid(agencyDTO.getDistrictId());

        agency.setDistrict(district);
        agencyService.save(agency);
        return agency;
    }
    @PutMapping("/agencies")
    public Agency updateAgency(@RequestBody AgencyDTO agencyDTO){
        Agency agency = agencyService.findByid(agencyDTO.getId());
        agency.setId(agencyDTO.getId());
        agency.setName(agencyDTO.getName());
        agency.setAddress(agencyDTO.getAddress());
        District district = districService.findByid(agencyDTO.getDistrictId());

        agency.setDistrict(district);
        agencyService.save(agency);
        return agency;
    }
    @DeleteMapping("/agencies/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        agencyService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted agency id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }

    @PostMapping("/agencies/upimg/{id}")
    public ResponseEntity<?> imgUpload(@RequestParam("img") MultipartFile file, @PathVariable("id") Long id, HttpServletRequest request) throws IOException, ServletException {
        if (file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain img");
        }
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            String urlImg=amazonUploadService.uploadFile(file);
            System.out.println(urlImg);
            Agency agency = agencyService.findByid(id);
            agency.setImage(urlImg);
            agencyService.save(agency);
            return ResponseEntity.ok().build();
        }else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpg or png");

    }
}
