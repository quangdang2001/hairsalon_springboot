package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Payment;
import com.project.hairsalon.model.Services;
import com.project.hairsalon.repo.ServicesRepo;
import com.project.hairsalon.service.IServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServicesService implements IServicesService {

    private final ServicesRepo servicesRepo;


    @Override
    public List<Services> findAll() {
        return servicesRepo.findAll();
    }

    @Override
    public Services findByid(Long id) {
        Optional<Services> result = servicesRepo.findById(id);
        Services services = null;
        if (result.isPresent()) {
            services=result.get();
        }
        else {
            return null;
        }
        return services;
    }

    @Override
    public void save(Services services) {
        servicesRepo.save(services);
    }

    @Override
    public void deleteById(Long id) {
        servicesRepo.deleteById(id);
    }
}
