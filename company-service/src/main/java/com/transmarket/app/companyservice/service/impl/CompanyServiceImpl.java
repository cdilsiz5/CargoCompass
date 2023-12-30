package com.transmarket.app.companyservice.service.impl;

import com.transmarket.app.companyservice.constant.CompanyConstant;
import com.transmarket.app.companyservice.dto.CompanyDto;
import com.transmarket.app.companyservice.exception.CompanyNotFoundException;
import com.transmarket.app.companyservice.mapper.CompanyMapper;
import com.transmarket.app.companyservice.model.Company;
import com.transmarket.app.companyservice.repository.CompanyRepository;
import com.transmarket.app.companyservice.request.CreateCompanyRequest;
import com.transmarket.app.companyservice.request.UpdateCompanyRequest;
import com.transmarket.app.companyservice.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {
    private final CompanyRepository repository;

    @Override
    @Transactional
    public CompanyDto createCompany(CreateCompanyRequest request) {
        Company company= CompanyMapper.COMPANY_MAPPER.createCompany(request);
        log.info("Creating company with request: {}", company);
        return CompanyMapper.COMPANY_MAPPER.toCompanyDto(repository.save(company));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDto getCompanyById(Long id) {
        Company company=repository.findById(id).orElseThrow(()-> {
            log.error("Company not found with id: {}", id);
            return new CompanyNotFoundException(CompanyConstant.COMPANY_NOT_FOUND);
        });
        log.info("Fetching Company with id : {}",id);
        return CompanyMapper.COMPANY_MAPPER.toCompanyDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDto getCompanyByUserId(Long id) {
        Company company=repository.findByUserId(id).orElseThrow(()-> {
            log.error("Company not found with user id: {}", id);
            return new CompanyNotFoundException(CompanyConstant.COMPANY_NOT_FOUND);
        });
        return CompanyMapper.COMPANY_MAPPER.toCompanyDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompanies() {
        log.info("Fetching all companies");
        return CompanyMapper.COMPANY_MAPPER.toCompanyDtoList(repository.findAll());
     }

    @Override
    @Transactional
    public CompanyDto updateCompany(Long id, UpdateCompanyRequest updateRequest) {
        Company company = repository.findById(id).orElseThrow(() -> {
            log.error("Company not found with id: {}", id);
            return new CompanyNotFoundException(CompanyConstant.COMPANY_NOT_FOUND);
        });
        Company companyRequest =updateCompanyFromRequest(company,updateRequest);
        Company updatedCompany=repository.save(company);
        log.info("Updated cargo with id: {}", updatedCompany.getId());
        return CompanyMapper.COMPANY_MAPPER.toCompanyDto(updatedCompany);
    }
    @Override
    @Transactional
    public void deleteCompany(Long id) {
        Company company = repository.findById(id).orElseThrow(() -> {
            log.error("Company not found with id: {}", id);
            return new CompanyNotFoundException(CompanyConstant.COMPANY_NOT_FOUND);
        });
        log.info("deleting company by id : {}",id);
        repository.deleteById(id);
    }
    private Company updateCompanyFromRequest(Company company, UpdateCompanyRequest request) {
        if (request.getName() != null && !request.getName().isEmpty()) {
            company.setName(request.getName());
        }
        if (request.getRegistrationNumber() != null && !request.getRegistrationNumber().isEmpty()) {
            company.setRegistrationNumber(request.getRegistrationNumber());
        }
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            company.setAddress(request.getAddress());
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            company.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            company.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getEstablishmentDate() != null) {
            company.setEstablishmentDate(request.getEstablishmentDate());
        }

        return company;
    }
}
