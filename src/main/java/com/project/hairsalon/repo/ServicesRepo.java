package com.project.hairsalon.repo;

import com.project.hairsalon.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepo extends JpaRepository<Services,Long> {
}
