package mx.edu.tecdesoftware.market_backend_2026_3_a.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@Tag(name = "Purchase", description = "Purchase in the store")
public class PurchaseController {
    /*
    listado completo, filtrar por cliente, registrar una nueva compra 
    codigos de estado, 
     */

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @GetMapping("/")
    @Operation(
            summary = "Get all purchases",
            description = "Return a List of all purchases"
    )
    // que valor esperas que te responda
    @ApiResponse(
            responseCode = "200",
            description = "Successfull retrieval of purchases"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/client/{clientId}")
    @Operation(
            summary = "Get a cliente by id",
            description = "Return a client"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Client found in the data base"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Client not found in data base"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity<Purchase> getByCliente(@PathVariable @Parameter(description = "client Id", example = "2552243", required = true) String clientId){
        return purchaseService.getByClientId(clientId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(
            summary = "Save a new purchase",
            description = "Register a new purchase and return the created purchase",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example purchase",
                                    value = """
                                            {
                                            "clientId": "2552243",
                                            "paymentMethod": "E",
                                            "comment": "string",
                                            "state": "p",
                                            "items": [
                                            {
                                            "productId": 5,
                                            "amount": 1,
                                            "total": 4000,
                                            "active": true
                                            }
                                            ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase conflict (duplicate)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }


}
