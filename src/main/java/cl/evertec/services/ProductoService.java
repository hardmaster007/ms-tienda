package cl.evertec.services;

import java.util.List;
import java.util.Optional;

import cl.evertec.models.entity.Producto;

public interface ProductoService {
	
	List<Producto> findAll();
	
	Optional<Producto> findById(Long id);

}
