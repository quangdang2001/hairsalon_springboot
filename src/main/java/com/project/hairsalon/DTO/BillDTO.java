package com.project.hairsalon.DTO;

import com.project.hairsalon.model.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class BillDTO {
    private Long id;

    private String clientPhone;

    private String employeeId;

    private Long paymentId;

    private Boolean status;

    private List<Long> idServices;

    private Long shiftId;

}
