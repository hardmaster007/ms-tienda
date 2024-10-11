package cl.evertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import cl.evertec.models.entity.Cliente;
import cl.evertec.services.ClienteService;

public class ClienteController<LoginRequest> {
	
	@Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.registrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Cliente autenticado = clienteService.autenticarCliente(((Cliente) request).getEmail(), ((Cliente) request).getPassword());
        if (autenticado != null) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    
    
    @PostMapping("/verificar")
    public ResponseEntity<Cliente> autenticarCliente(@RequestHeader("Authorization") String token) {
        // Quitamos la palabra "Bearer " del token si está presente
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        Cliente cliente = clienteService.autenticarCliente(token, token);
        return ResponseEntity.ok(cliente);
    }

}
