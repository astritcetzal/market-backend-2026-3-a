package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Query method
    /*
        SELECT *
        FROM categorias
        WHERE id_categoria = ?
        ORDER BY nombre ASC
    * */
    //Obtener una lista de profectos filtrados por id de categoria
    // y ordenados ascendenytemente por nombre
    // sintaxis en query method
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Obtener los productos
    //de manera opcional te dará ciertos datos
    Optional<List<Producto>>findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);


}
