package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//le dices a spring que está clase se comunica con la bd
    public class ProductoRepository implements ProductRepository {

        @Autowired
        private ProductoCrudRepository productoCrudRepository;
        @Autowired
        private ProductMapper productMapper;

        //SELECT * FROM productos
    public List<Product> getAll(){
        // se "castear" de iterable a la lista

        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        /*aqui entra el mapper todo lo que esté en español es para a productos*/
        return productMapper.toProducts(productos);
    }
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos=productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }
    /**
     SELECT *
     FROM producto
     WHERE cantidad_stock < ?
     AND estado = true;
     */
    public Optional<List<Product>> getScarceProducts (int quantity){

            Optional<List<Producto>>productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
            return Optional.of(productMapper.toProducts(productos.get()));
        }
    // obtener un producto dado el id
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId)
                .map(producto -> productMapper.toProduct(producto));
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?,?,?,?);
    */
    //guardar un producto
    public Product save(Product product){
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }
    //Eliminar por iD
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

    }


