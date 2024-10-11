package cl.evertec.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.evertec.models.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Optional<Cliente> findByEmail(String email);

}
