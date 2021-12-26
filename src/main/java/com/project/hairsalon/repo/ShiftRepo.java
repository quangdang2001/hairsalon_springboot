package com.project.hairsalon.repo;

import com.project.hairsalon.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepo extends JpaRepository<Shift,Long> {
    List<Shift> findShiftsByShiftDate(Date shiftDate);
}
