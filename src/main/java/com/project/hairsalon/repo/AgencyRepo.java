package com.project.hairsalon.repo;

import com.project.hairsalon.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepo extends JpaRepository<Agency,Long> {
}
