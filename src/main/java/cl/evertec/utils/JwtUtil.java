package cl.evertec.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import cl.evertec.models.entity.Cliente;

import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "secret";  // Llave secreta para firmar el token

    public String generateToken(Cliente cliente) {
        return Jwts.builder()
                .setSubject(cliente.getEmail())
                .claim("id", cliente.getId())
                .claim("name", cliente.getNombre())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 horas de expiración
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


 

   


	public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return null;
	}







	public boolean validateToken(String token, Cliente cliente) {
        final String email = extractEmail(token);
        return (email.equals(cliente.getEmail()) && !isTokenExpired(token));
    }




	private String extractEmail(String token) {
		// TODO Auto-generated method stub
		return null;
	}
}
