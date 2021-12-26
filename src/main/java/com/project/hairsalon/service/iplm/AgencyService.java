package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Agency;
import com.project.hairsalon.repo.AgencyRepo;
import com.project.hairsalon.service.IAgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class AgencyService implements IAgencyService {
    private final AgencyRepo agencyRepo;

    @Override
    public List<Agency> findAll() {
        return agencyRepo.findAll() ;
    }

    @Override
    public Agency findByid(Long id) {
        Optional<Agency> result = agencyRepo.findById(id);
        Agency agency = null;
        if (result.isPresent()) {
            agency=result.get();
        }
        else {
            return null;
        }
        return agency;
    }

    @Override
    public void save(Agency agency) {
        agencyRepo.save(agency);
    }

    @Override
    public void deleteById(Long id) {
        agencyRepo.deleteById(id);
    }
}
