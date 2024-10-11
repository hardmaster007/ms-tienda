package cl.evertec.models.repository;

import org.springframework.data.repository.CrudRepository;

import cl.evertec.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
