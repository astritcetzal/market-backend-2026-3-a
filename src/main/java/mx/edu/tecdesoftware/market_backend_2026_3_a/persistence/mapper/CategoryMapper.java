package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Category;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//va a traducir de lo que está en la bd
@Mapper(componentModel = "spring")
public interface CategoryMapper {
   @Mappings({
           @Mapping(source = "idCategoria", target ="categoryId"),
           @Mapping(source = "descripcion", target ="category"),
           @Mapping(source = "estado", target ="active")
   })
   // para transformar una entidad de categoria a category y el segunda en viceversa
   Category toCategory(Categoria categoria);

        @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
