package cl.evertec.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.evertec.models.entity.Producto;
import cl.evertec.models.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService {
	
	
	final private ProductoRepository repo;
	
	public ProductoServiceImpl(ProductoRepository repo) {
		this.repo = repo;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long id) {
		return repo.findById(id);
	}

}
