/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import model.pojos.ResponseService;
import model.pojos.SesionToken;

/**
 *
 * @author michi
 */
public class AuthorizationTokenJsonWebToken {

    public static SesionToken generateToken(SesionToken sesionToken) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("security.configurationJsonWebToken");
        String SECRET_KEY = resourceBundle.getString("SECRET_KEY");
        Integer MINUTES_EXPIRATION_TIME = 10;
        try {
            MINUTES_EXPIRATION_TIME = Integer.parseInt(resourceBundle.getString("MINUTES_EXPIRATION_TIME"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        Date now = currentTime.getTime();
        currentTime.add(Calendar.MINUTE, MINUTES_EXPIRATION_TIME);
        Date EXPIRATION_TIME = currentTime.getTime();
        String token = Jwts.builder()
                .setSubject(sesionToken.getName())
                .setIssuer(sesionToken.getName())
                .setIssuedAt(now)
                .setExpiration(EXPIRATION_TIME)
                .claim("id", sesionToken.getId())
                .claim("nombre", sesionToken.getName())
                .claim("email", sesionToken.getEmail())
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .compact();
        sesionToken.setTokenacceso(token);
        return sesionToken;
    }

    public static ResponseService validateToken(String token) {
        ResponseService responseService = new ResponseService();
        ResourceBundle bundle = ResourceBundle.getBundle("security.configurationJsonWebToken");
        String SECRET_KEY = bundle.getString("SECRET_KEY");
        if (token == null || token.isEmpty()) {
            responseService.setError(true);
            responseService.setMessage("The token is null or empty...");
        } else {
            try {
                Claims TOKEN_DESCIFRADO = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
                SesionToken sesion = new SesionToken();
                sesion.setId((Integer) TOKEN_DESCIFRADO.get("id"));
                sesion.setName((String) TOKEN_DESCIFRADO.get("nombre"));
                sesion.setEmail((String) TOKEN_DESCIFRADO.get("email"));
                responseService.setSesionToken(sesion);
                responseService.setError(false);
                responseService.setMessage("OK");
            } catch (ExpiredJwtException expExc) {
                responseService.setError(true);
                responseService.setMessage("Token has expired");
            } catch (SignatureException e) {
                responseService.setError(true);
                responseService.setMessage("Token with invalid signature");
            } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
                responseService.setError(true);
                responseService.setMessage("Invalid Token");
            } catch (Exception e) {
                responseService.setError(true);
                responseService.setMessage(e.getMessage());
            }
        }
        return responseService;
    }

}
