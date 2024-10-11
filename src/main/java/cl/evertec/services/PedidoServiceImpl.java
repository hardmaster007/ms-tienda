package cl.evertec.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import cl.evertec.models.entity.Cliente;
import cl.evertec.models.entity.Pedido;
import cl.evertec.models.repository.ClienteRepository;
import cl.evertec.models.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private ClienteRepository clienteRepository;

	final private PedidoRepository repoPed;

	public PedidoServiceImpl(PedidoRepository repoPed) {
		this.repoPed = repoPed;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pedido> obtenerPedido(Long id) {
		return repoPed.findById(id);
	}

	@Override
	public Pedido crearPedido(Pedido pedido) {
		return repoPed.save(pedido);
	}

	@Override
	public ResponseEntity<Cliente> listarPedidosPorCliente(Long clienteId) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
		if (clienteOptional.isPresent()) {
			return ResponseEntity.ok(clienteOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();

	}

	@Override
	public Pedido actualizarEstado(Long id, String nuevoEstado) {

		Pedido pedido = repoPed.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
		pedido.setEstado(nuevoEstado);
		return repoPed.save(pedido);
	}

	@Override
	public void cancelarPedido(Long id) {

		Pedido pedido = repoPed.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

		if (pedido.getEstado().equalsIgnoreCase("Procesando") || pedido.getEstado().equalsIgnoreCase("Enviado")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se puede cancelar un pedido que ya está procesado o enviado.");
		}

		pedido.setEstado("Cancelado");
		repoPed.save(pedido);

	}

}
