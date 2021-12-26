package com.project.hairsalon.controller;


import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.Payment;
import com.project.hairsalon.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private IClientService clientService;


    @GetMapping("/clients")
    public List<Client> getAllClient(){
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id){
        Client client = clientService.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(client);

    }

    @PostMapping(value = "/clients")
    public Client addClient(@RequestBody Client client){
        System.out.println(client.toString());
        client.setId(null);
        clientService.save(client);
        return client;
    }

    @PutMapping("/clients")
    public Client updateClient(@RequestBody Client client){
        clientService.save(client);
        return client;
    }

    @DeleteMapping("/clients")
    public ResponseEntity<?> deleteClient(@RequestParam Long id){

        clientService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted Client id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }

}
