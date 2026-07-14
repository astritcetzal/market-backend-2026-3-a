package mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//las condiciones en el servicio
//repositorio es donde estan almacenados todos los datos
//el servio solo la logica que usa los datos del repositorio
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public Optional<Product> getProduct(Integer productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(Integer categoryId){
        return productRepository.getByCategory(categoryId);

    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public boolean delete(int productId){
        if(getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        }{
            return false;
        }
    }



}
