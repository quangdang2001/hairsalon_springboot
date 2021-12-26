package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Category;
import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.Employee;
import com.project.hairsalon.repo.ClientRepo;
import com.project.hairsalon.repo.EmployeeRepo;
import com.project.hairsalon.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService implements IClientService, UserDetailsService {

    private final ClientRepo clientRepo;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client findByid(Long id) {
        Optional<Client> result = clientRepo.findById(id);
        Client client = null;
        if (result.isPresent()) {
            client=result.get();
        }
        else {
            return null;
        }
        return client;
    }

    @Override
    public void save(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepo.save(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public Client findClientByPhone(String phone) {
        return clientRepo.findClientByPhone(phone);
    }

    @Override
    public Client findClientByPhoneAndPassword(String phone, String password) {
        return clientRepo.findClientByPhoneAndPassword(phone,password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client =findClientByPhone(username);
        if (client!=null){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(client.getPhone(),client.getPassword(),authorities);
        }
        Employee employee = employeeRepo.findEmployeeByPhone(username);
        if (employee!=null){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(employee.getRole()));
            return new User(employee.getPhone(),employee.getPassword(),authorities);
        }
        if (client == null && employee == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return null;
    }
}
