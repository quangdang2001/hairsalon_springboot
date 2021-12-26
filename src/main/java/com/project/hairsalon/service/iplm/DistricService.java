package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Client;
import com.project.hairsalon.model.District;
import com.project.hairsalon.repo.DistricRepo;
import com.project.hairsalon.service.IDistricService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DistricService implements IDistricService {

    @Autowired
    private final DistricRepo districRepo;


    @Override
    public List<District> findAll() {
        return districRepo.findAll();
    }

    @Override
    public District findByid(Long id) {
        Optional<District> result = districRepo.findById(id);
        District district = null;
        if (result.isPresent()) {
            district=result.get();
        }
        else {
            return null;
        }
        return district;
    }

    @Override
    public void save(District district) {
        districRepo.save(district);
    }

    @Override
    public void deleteById(Long id) {
        districRepo.deleteById(id);
    }

    @Override
    public District findByName(String name) {
        return districRepo.findDistinctByName(name);
    }
}
