package com.transmarket.app.companyservice.mapper;


import com.transmarket.app.companyservice.model.Company;
import com.transmarket.app.companyservice.dto.CompanyDto;
import com.transmarket.app.companyservice.request.CreateCompanyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CompanyMapper {

        CompanyMapper COMPANY_MAPPER= Mappers.getMapper(CompanyMapper.class);

        CompanyDto toCompanyDto(Company company);

        List<CompanyDto> toCompanyDtoList(List<Company> companyList);

        Company createCompany(CreateCompanyRequest request);

        void updateCompany(@MappingTarget Company Company, CreateCompanyRequest request);


}
