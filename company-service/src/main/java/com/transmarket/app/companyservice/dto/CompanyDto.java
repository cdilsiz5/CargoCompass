package com.transmarket.app.companyservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private Long id;
    private String name;

    private String registrationNumber;

    private String address;

    private String email;

    private String phoneNumber;

    private Date establishmentDate;

    private Long userId;

}
