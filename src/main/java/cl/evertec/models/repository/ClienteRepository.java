package cl.evertec.models.repository;

import org.springframework.data.repository.CrudRepository;

import cl.evertec.models.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
