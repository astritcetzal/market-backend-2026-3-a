package mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory (Integer categoryId);
    Optional<List<Product>>  getScarceProducts (int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productoId);
}
