package com.transmarket.app.companyservice.controller;

import com.transmarket.app.companyservice.constant.CompanyConstant;
import com.transmarket.app.companyservice.dto.CompanyDto;
import com.transmarket.app.companyservice.request.CreateCompanyRequest;
import com.transmarket.app.companyservice.request.UpdateCompanyRequest;
import com.transmarket.app.companyservice.service.ICompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CompanyConstant.API_PREFIX+ CompanyConstant.API_VERSION_V1+ CompanyConstant.API_COMPANY)
@RequiredArgsConstructor
@Tag(
        name = "CRUD REST APIs for Company in Trans Market",
        description = "CRUD REST APIs in Trans Market to CREATE, UPDATE, FETCH AND DELETE account details"
)
@CrossOrigin
public class CompanyController {

    private final ICompanyService companyService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Company inside Trans Market"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = CompanyDto.class),
                            mediaType = "application/json")))

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto createCompany(@Valid @RequestBody CreateCompanyRequest createRequest){
        return companyService.createCompany(createRequest);
    }
    @Operation(
            summary = "Fetch Cargo Details REST API",
            description = "REST API to fetch Company details based on a company id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CompanyDto.class),
                            mediaType = "application/json")))
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto getCompanyById(@PathVariable Long id){
        return  companyService.getCompanyById(id);
    }
    @Operation(
            summary = "Fetch Cargo Details REST API",
            description = "REST API to fetch Company details List"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = List.class),
                            mediaType = "application/json")))
    @GetMapping("/fetch")
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> getAllCompanies(){
        return companyService.getAllCompanies();
    }
    @Operation(
            summary = "Update Cargo Details REST API",
            description = "REST API to update Company details based on company id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CompanyDto.class),
                            mediaType = "application/json")))
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto updateCompany(@PathVariable Long id,@Valid @RequestBody UpdateCompanyRequest updateRequest){
        return  companyService.updateCompany(id,updateRequest);
    }

    @Operation(
            summary = "Delete Company Details REST API",
            description = "REST API to  Delete Company details based on company id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }
    @Operation(
            summary = "Fetch Company Details REST API",
            description = "REST API to Delete Cargo details based on user id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CompanyDto.class),
                            mediaType = "application/json")))
    @GetMapping("/userId/{userId}")
    public CompanyDto getCompanyByUserId(@PathVariable Long userId) {
        return companyService.getCompanyByUserId(userId);
    }

}
