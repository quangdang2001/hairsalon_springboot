package com.project.hairsalon.repo;

import com.project.hairsalon.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistricRepo extends JpaRepository<District,Long> {
    District findDistinctByName(String name);
}
