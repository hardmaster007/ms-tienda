package cl.evertec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.evertec.models.entity.Producto;
import cl.evertec.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	final private ProductoService serviceProd;

	public ProductoController(ProductoService serviceProd) {
		this.serviceProd = serviceProd;
	}

	@GetMapping
	public List<Producto> listarProductos() {
		return serviceProd.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
		Optional<Producto> productOptional = serviceProd.findById(id);
		if (productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

}
