package cl.evertec.models.repository;

import org.springframework.data.repository.CrudRepository;

import cl.evertec.models.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
