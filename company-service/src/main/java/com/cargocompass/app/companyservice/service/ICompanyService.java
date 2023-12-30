package com.cargocompass.app.companyservice.service;


import com.cargocompass.app.companyservice.dto.CompanyDto;
import com.cargocompass.app.companyservice.request.CreateCompanyRequest;
import com.cargocompass.app.companyservice.request.UpdateCompanyRequest;

import java.util.List;

public interface ICompanyService {

    /**
     * Creates a new company.
     *
     * @param createRequest the request data for creating a new company
     * @return the DTO of the created company
     */
    CompanyDto createCompany(CreateCompanyRequest createRequest);

    /**
     * Retrieves a company by its ID.
     *
     * @param id the ID of the company
     * @return the DTO of the corresponding company
     */
    CompanyDto getCompanyById(Long id);

    /**
     * Retrieves a company by a user's ID.
     *
     * @param userId the ID of the user associated with the company
     * @return the DTO of the corresponding company
     */
    CompanyDto getCompanyByUserId(Long userId);

    /**
     * Lists all companies.
     *
     * @return a list of all companies
     */
    List<CompanyDto> getAllCompanies();

    /**
     * Updates a specific company.
     *
     * @param id the ID of the company to update
     * @param updateRequest the update request data for the company
     * @return the DTO of the updated company
     */
    CompanyDto updateCompany(Long id, UpdateCompanyRequest updateRequest);

    /**
     * Deletes a specific company.
     *
     * @param id the ID of the company to be deleted
     */
    void deleteCompany(Long id);
}

