package mx.edu.tecdesoftware.market_backend_2026_3_a.web.controller;

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
public class ProductController {
    // para inyectar dependencias
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll(ProductService productService){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    // Obtener los productos por categoira, guardar y borrar productos
    // luego de eso a compras
    // que hace @PathVariable
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    //que hace requestbody
    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int productId){
        if (productService.delete(productId)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
