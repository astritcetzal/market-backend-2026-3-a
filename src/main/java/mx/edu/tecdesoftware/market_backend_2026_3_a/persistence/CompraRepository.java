package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.ProductMapper;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    public List<Purchase> getAll(){
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    public Optional<Purchase> getByClientId(String clienteId){
        return compraCrudRepository.findByIdCliente(clienteId).map(compra -> purchaseMapper.toPurchase(compra));
    }
    @Override
    public Purchase save(Purchase purchase){
        Compra compra = purchaseMapper.toCompra(purchase);
        if (compra.getProducto() != null) {
            compra.getProducto().forEach(producto -> producto.setCompra(compra));
        }
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
