package co.edu.uniquindio.proyecto.filter;

import co.edu.uniquindio.proyecto.dto.ProductoFilter;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductoSpecification implements Specification<Producto> {

    @Getter @Setter
    private ProductoFilter productoFilter;

    public ProductoSpecification(ProductoFilter productoFilter) {
        this.productoFilter = productoFilter;
    }

    @Override
    public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (productoFilter.getNombre() != null) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.upper(root.get("nombre")), "%"+productoFilter.getNombre().toUpperCase()+"%"), criteriaBuilder.like(criteriaBuilder.upper(root.get("descripcion")), "%"+productoFilter.getNombre().toUpperCase()+"%")));
        }

        if (productoFilter.getCategoria() != null) {
            predicates.add(criteriaBuilder.isMember(productoFilter.getCategoria() ,root.get("categorias")));
        }
        if (productoFilter.getPrecioMinimo() != null && productoFilter.getPrecioMaximo() != null) {
            predicates.add(criteriaBuilder.between(root.get("precio"), productoFilter.getPrecioMinimo(), productoFilter.getPrecioMaximo()));
        }
        if (productoFilter.getCiudad() != null) {
            predicates.add(criteriaBuilder.equal(root.get("ciudad"), productoFilter.getCiudad()));
        }
        if (productoFilter.getCalificacionMinima() != null && productoFilter.getCalificacionMaxima() != null) {

            //predicates.add(criteriaBuilder.between(, productoFilter.getCalificacionMinima(), productoFilter.getCalificacionMaxima()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
