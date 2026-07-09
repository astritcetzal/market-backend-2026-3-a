package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository {
    List<Compra> findBy(String idcliente);

   Optional<List<Producto>> findByIdCliente(int cantidad, boolean estado);


}
