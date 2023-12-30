package com.cargocompass.app.offerservice.controller;

import com.cargocompass.app.offerservice.dto.OfferDto;
import com.cargocompass.app.offerservice.model.enums.OfferStatus;
import com.cargocompass.app.offerservice.request.CreateOfferRequest;
import com.cargocompass.app.offerservice.request.UpdateOfferRequest;
import com.cargocompass.app.offerservice.service.IOfferService;
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

import static com.cargocompass.app.offerservice.constant.OfferConstant.*;

@RestController
@RequestMapping(API_PREFIX+API_VERSION_V1+API_OFFER)
@RequiredArgsConstructor
@CrossOrigin
@Tag(
            name = "CRUD REST APIs for Offer in Trans Market",
        description = "CRUD REST APIs in Trans Market to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class OfferController {

    private final IOfferService offerService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Offer inside Trans Market"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = OfferDto.class),
                            mediaType = "application/json")))


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto createOffer(@Valid @RequestBody CreateOfferRequest request) {
        return offerService.createOffer(request);
    }

    @Operation(
            summary = "Fetch Offer Details REST API",
            description = "REST API to fetch Offer details based on a cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = OfferDto.class),
                            mediaType = "application/json")))
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OfferDto getOfferById(@PathVariable Long id){
        return offerService.getOfferById(id);
    }
    @Operation(
                summary = "Fetch Offer Details REST API",
            description = "REST API to fetch Offer details List"
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
        public List<OfferDto> getAllOffers(){
            return offerService.getAllOffers();
    }
    @Operation(
            summary = "Update Offer Details REST API",
            description = "REST API to update Offer details based on cargo id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = OfferDto.class),
                            mediaType = "application/json")))
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
        public OfferDto updateOffer(@PathVariable Long id,@Valid @RequestBody UpdateOfferRequest updateRequest){
            return offerService.updateOffer(id,updateRequest);
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
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);
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
                            schema = @Schema(implementation = OfferDto.class),
                            mediaType = "application/json")))
    @PutMapping("/update/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    OfferDto updateOfferStatus(@PathVariable Long id,@Valid @RequestBody OfferStatus newStatus){
        return offerService.updateOfferStatus(id,newStatus);
    }

}