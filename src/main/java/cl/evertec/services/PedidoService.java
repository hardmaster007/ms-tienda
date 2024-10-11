package cl.evertec.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import cl.evertec.models.entity.Cliente;
import cl.evertec.models.entity.Pedido;

public interface PedidoService {

	Optional<Pedido> obtenerPedido(Long id);

	Pedido crearPedido(Pedido pedido);

	ResponseEntity<Cliente> listarPedidosPorCliente(Long clienteId);

	Pedido actualizarEstado(Long id, String nuevoEstado);

	void cancelarPedido(Long id);
}
