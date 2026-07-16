package mx.edu.tecdesoftware.market_backend_2026_3_a.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.ContentType;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Manage products in the store")
public class ProductController {
    // para inyectar dependencias
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    @Operation(
            summary = "Get all products",
            description = "Return a List of all available products"
    )
    // que valor esperas que te responda
    @ApiResponse(
            responseCode = "200",
            description = "Successfull retrieval of products"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    //bad request -> 400
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Return a product by its ID id it exists"
    )
    // que valor esperas que te responda
    @ApiResponse(
            responseCode = "200",
            description = "Product found"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "ID of the product retrieved", example = "7", required = true)
            @PathVariable("id") Integer productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    // Obtener los productos por categoira, guardar y borrar productos
    // luego de eso a compras
    // que hace @PathVariable
    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get a product by category",
            description = "Return all products in a specifc category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product found in the category "
    )
    @ApiResponse(
            responseCode = "404",
            description = "Product not found in the category"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
    )
    public ResponseEntity<List<Product>> getByCategory(
            @PathVariable @Parameter(description = "Category ID", example = "2", required = true)
            int categoryId){
        return productService.getByCategory(categoryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    //que hace requestbody
    @PostMapping("")
    @Operation(
            summary = "Save a new product",
            description = "Register a new product and return the created product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example product",
                                    value = """
                                            {
                                            "name":"Mirinda",
                                            "category": "5",
                                            "price":"20.5",
                                            "stock" : 300,
                                            "active" : true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary  ="Delete a product by ID", description = "Delete a product if it exists")
    @ApiResponse(responseCode = "201", description = "Product delete successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")


    public ResponseEntity delete(
            @Parameter(description = "ID of the product to be deleted", example="7", required = true)
            @PathVariable("id") Integer productId){
        if (productService.delete(productId)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
