package cl.evertec.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.evertec.models.entity.Cliente;
import cl.evertec.models.entity.Pedido;
import cl.evertec.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	final private PedidoService pedidoService;

	public PedidoController(PedidoService servicePed) {
		this.pedidoService = servicePed;

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
		Optional<Pedido> pedidoOptional = (Optional<Pedido>) pedidoService.obtenerPedido(id);
		if (pedidoOptional.isPresent()) {
			return ResponseEntity.ok(pedidoOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
		Pedido nuevoPedido = pedidoService.crearPedido(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
	}

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<Cliente> listarPedidosPorCliente(@PathVariable Long clienteId) {
		return pedidoService.listarPedidosPorCliente(clienteId);
	}

	@PutMapping("/{id}/estado")
	public ResponseEntity<Pedido> actualizarEstadoPedido(@PathVariable Long id, @RequestBody String nuevoEstado) {
		Pedido pedidoActualizado = pedidoService.actualizarEstado(id, nuevoEstado);
		return ResponseEntity.ok(pedidoActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
		pedidoService.cancelarPedido(id);
		return ResponseEntity.noContent().build();
	}

}
