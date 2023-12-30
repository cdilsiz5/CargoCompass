package com.transmarket.app.vehicleservice.controller;


import com.transmarket.app.vehicleservice.dto.VehicleDto;
import com.transmarket.app.vehicleservice.request.CreateVehicleRequest;
import com.transmarket.app.vehicleservice.request.UpdateVehicleRequest;
import com.transmarket.app.vehicleservice.service.IVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.transmarket.app.vehicleservice.constant.VehicleConstant.*;


@RestController
@Tag(
        name = "CRUD REST APIs for Vehicle in Trans Market",
        description = "CRUD REST APIs in Trans Market to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RequestMapping(API_PREFIX + API_VERSION_V1 + API_VEHICLES)
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class VehicleController {

    private final IVehicleService vehicleService;

    @Operation(
            summary = "Create Vehicle REST API",
            description = "REST API to create new Vehicle inside Trans Market"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                        schema = @Schema(implementation = VehicleDto.class),
                        mediaType = "application/json")))

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDto createVehicle(@Valid @RequestBody CreateVehicleRequest request){
        return vehicleService.createVehicle(request);
    }


    @Operation(
            summary = "Fetch Vehicle Details REST API",
            description = "REST API to fetch Vehicle details based on a Vehicle id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = VehicleDto.class),
                            mediaType = "application/json")))
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDto getVehicleById(@PathVariable Long id){
        return vehicleService.getVehicleById(id);
    }



    @Operation(
            summary = "Fetch Vehicle Details REST API",
            description = "REST API to fetch Vehicle details List"
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
    public List<VehicleDto> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @Operation(
            summary = "Update Vehicle Details REST API",
            description = "REST API to update Vehicle details based on Vehicle id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = VehicleDto.class),
                            mediaType = "application/json")))
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDto updateVehicle(@PathVariable Long id, @Valid @RequestBody UpdateVehicleRequest updateRequest){
        return vehicleService.updateVehicle(id,updateRequest);
    }


    @Operation(
            summary = "Delete Vehicle Details REST API",
            description = "REST API to  Delete Vehicle details based on Vehicle id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"))
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }


}
