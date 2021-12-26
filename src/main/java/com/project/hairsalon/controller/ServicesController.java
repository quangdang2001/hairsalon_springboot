package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.DTO.ServicesDTO;
import com.project.hairsalon.model.Category;
import com.project.hairsalon.model.Services;
import com.project.hairsalon.service.ICategoryService;
import com.project.hairsalon.service.IServicesService;
import com.project.hairsalon.utils.AmazonUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
public class ServicesController {
    @Autowired
    private IServicesService servicesService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private AmazonUploadService amazonUploadService;

    @GetMapping("/services")
    public List<Services> getAllAgency(){
        return servicesService.findAll();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id){
        Services services = servicesService.findByid(id);

        return ResponseEntity.status(HttpStatus.OK).body(services);

    }

    @PostMapping("/services")
    public Services addAgency(@RequestBody ServicesDTO servicesDTO){
        Services services = new Services();
        services.setId(null);
        services.setName(servicesDTO.getName());
        services.setDescription(servicesDTO.getDescription());
        services.setPrice(servicesDTO.getPrice());

        Category category = categoryService.findByid(servicesDTO.getCategoryId());
        services.setCategory(category);

        servicesService.save(services);
        return services;
    }

    @PostMapping(path = "/services/upimg/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> imgUpload(@RequestParam("img") MultipartFile file, @PathVariable("id") Long id, HttpServletRequest request) throws IOException, ServletException {
        if (file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain img");
        }
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            String urlImg=amazonUploadService.uploadFile(file);
            System.out.println(urlImg);
            Services services = servicesService.findByid(id);
            services.setImage(urlImg);
            servicesService.save(services);
            return ResponseEntity.ok().build();
        }else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpg or png");

    }

    @PutMapping("/services")
    public Services updateAgency(@RequestBody ServicesDTO servicesDTO){
        Services services = servicesService.findByid(servicesDTO.getId());

        services.setId(servicesDTO.getId());
        services.setDescription(servicesDTO.getDescription());
        services.setPrice(servicesDTO.getPrice());
        services.setName(services.getName());
        Category category = categoryService.findByid(servicesDTO.getCategoryId());
        services.setCategory(category);

        servicesService.save(services);
        return services;
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        Services services = servicesService.findByid(id);
        if (services.getImage()!=null && !services.getImage().equals("")) {
            amazonUploadService.deleteFile(services.getImage());
        }
        servicesService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted Service id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
}
