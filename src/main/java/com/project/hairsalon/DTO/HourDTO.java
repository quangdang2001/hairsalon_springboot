package com.project.hairsalon.DTO;

import com.project.hairsalon.model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class HourDTO {
    private Shift shift;
    private Boolean isWork;
}
