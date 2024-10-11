package cl.evertec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import cl.evertec.models.entity.Cliente;
import cl.evertec.models.repository.ClienteRepository;
import cl.evertec.utils.JwtUtil;

public class ClienteServiceImpl implements ClienteService{

	@Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  

    @Autowired
    private JwtUtil jwtUtil; 

    @Override
    public Cliente registrarCliente(Cliente cliente) {
    
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está en uso.");
        }

        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente autenticarCliente(String email, String password) {
        Cliente cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        if (!passwordEncoder.matches(password, cliente.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos");
        }


        return jwtUtil.generateToken(cliente);
    }

}
