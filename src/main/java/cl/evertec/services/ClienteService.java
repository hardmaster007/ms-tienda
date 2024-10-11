package cl.evertec.services;

import cl.evertec.models.entity.Cliente;

public interface ClienteService {

	Cliente registrarCliente(Cliente cliente);

	Cliente autenticarCliente(String email, String password);
	
	

}
