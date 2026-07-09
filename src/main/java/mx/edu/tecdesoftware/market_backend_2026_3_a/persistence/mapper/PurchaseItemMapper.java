package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
    public interface PurchaseItemMapper {

        @Mappings({
                @Mapping(source = "id.idProducto", target = "productId"),
                @Mapping(source = "cantidad", target = "amount"),
                // total se mapea automático porque se llaman igual
                @Mapping(source = "estado", target = "active")
        })
        PurchaseItem toPurchaseItem(CompraProducto producto);

        @InheritInverseConfiguration
        @Mappings({
                @Mapping(target = "compra", ignore = true),
                @Mapping(target = "producto", ignore = true),
                @Mapping(target = "id.idCompra", ignore = true)
        })
        CompraProducto toCompraProducto(PurchaseItem item);
}
