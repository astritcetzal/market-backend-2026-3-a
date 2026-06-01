package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;
    //SELECT * FROM productos
public List<Producto> getAll(){
    // se "castear" de iterable a la lista
    return (List<Producto>) productoCrudRepository.findAll();
}
}


