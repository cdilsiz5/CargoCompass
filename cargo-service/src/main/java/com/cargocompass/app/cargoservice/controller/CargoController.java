package com.cargocompass.app.cargoservice.controller;

import com.cargocompass.app.cargoservice.dto.CargoDto;
import com.cargocompass.app.cargoservice.model.enums.CargoStatus;
import com.cargocompass.app.cargoservice.request.CreateCargoRequest;
import com.cargocompass.app.cargoservice.request.UpdateCargoRequest;
import com.cargocompass.app.cargoservice.service.ICargoService;
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

import static com.cargocompass.app.cargoservice.constant.CargoConstant.*;

@RestController
@Tag(
        name = "CRUD REST APIs for Cargo in Trans Market",
        description = "CRUD REST APIs in Trans Market to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RequestMapping(API_PREFIX + API_VERSION_V1 + API_CARGOS)
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class CargoController {

    protected final ICargoService cargoService;

    @Operation(
            summary = "Create Cargo REST API",
            description = "REST API to create new Cargo inside Trans Market"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                        schema = @Schema(implementation = CargoDto.class),
                        mediaType = "application/json")))

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CargoDto createCargo(@Valid @RequestBody CreateCargoRequest request){
        return cargoService.createCargo(request);
    }


    @Operation(
            summary = "Fetch Cargo Details REST API",
            description = "REST API to fetch Cargo details based on a cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CargoDto.class),
                            mediaType = "application/json")))
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CargoDto getCargoById(@PathVariable Long id){
        return cargoService.getCargoById(id);
    }



    @Operation(
            summary = "Fetch Cargo Details REST API",
            description = "REST API to fetch Cargo details List"
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
    public List<CargoDto> getAllCargos() {
         return cargoService.getAllCargos();
    }

    @Operation(
            summary = "Update Cargo Details REST API",
            description = "REST API to update Cargo details based on cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CargoDto.class),
                            mediaType = "application/json")))
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CargoDto updateCargo(@PathVariable Long id,@Valid @RequestBody  UpdateCargoRequest updateRequest){
        return cargoService.updateCargo(id,updateRequest);
    }


    @Operation(
            summary = "Delete Cargo Details REST API",
            description = "REST API to  Delete Cargo details based on cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"))
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCargo(@PathVariable Long id){
        cargoService.deleteCargo(id);
    }

    @Operation(
            summary = "Update Cargo Details REST API",
            description = "REST API to  Update Cargo details based on cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CargoDto.class),
                            mediaType = "application/json")))
    @PutMapping("/update/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    CargoDto updateCargoStatus(@PathVariable Long id,@Valid @RequestBody CargoStatus newStatus){
        return cargoService.updateCargoStatus(id,newStatus);
    }

    @Operation(
            summary = "Fetch Cargo Details REST API",
            description = "REST API to  Fetch Cargo details based on cargo status "
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CargoDto.class),
                            mediaType = "application/json")))
    @GetMapping("/fetch/status")
    @ResponseStatus(HttpStatus.OK)
    public List<CargoDto> getCargosByStatus(@RequestParam CargoStatus status){
        return cargoService.getCargosByStatus(status);
    }

    @Operation(
            summary = "Fetch Cargo Details REST API",
            description = "REST API to  Fetch Cargo details based on origin and destination "
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CargoDto.class),
                            mediaType = "application/json")))
    @GetMapping("/fetch/origin")
    @ResponseStatus(HttpStatus.OK)
    public List<CargoDto> getCargosByOriginAndDestination(@RequestParam String origin,@RequestParam String destination) {
        return cargoService.getCargosByOriginAndDestination(origin,destination);
    }

}
