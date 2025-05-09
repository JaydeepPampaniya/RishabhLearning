package com.PhoneX.Backend.Service;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET = "U3VwZXJTZWNyZXRKV1RLZXlGb3JBdXRoMTIzNDU2Nzg5MA==";

    private final String secretKey;
    private static final int EXPIRATION_TIME = 1000 * 60 * 30;

    public JwtService() {
        secretKey = SECRET;
    }

    public String generateToken(Long id, String username, String gender, String role, String email, List<String> permissions) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", id);
        claims.put("gender", gender);
        claims.put("permissions", permissions);
        claims.put("role", role);
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey()) // We can chane SignatureAlgorithm.HS512
                .compact(); //build jwt with compact string
    }


    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*

    Applies claimResolver Function:
    Uses a Function<Claims, T> (a functional interface from Java 8) to specify which claim to extract.
    claimResolver is passed as an argument, which allows the method to be flexible and reusable.
    The function is then applied to the Claims object to extract the desired claim.

     */

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) { //Java Generics (<T>) to allow different types of claims to be extracted (e.g., String for username, List<String> for roles, Date for expiration, etc.).
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public List<String> extractPermissions(String token) {
        return extractClaim(token, claims -> (List<String>) claims.get("permissions"));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()//It creates a new instance of a parser object from the JJWT (Java JWT) library.
                .verifyWith(getKey())
                .build()//Builds the parser with the given verification key
                .parseSignedClaims(token)//Parses the JWT and verifies the signature. If the signature is invalid, an exception will be thrown.
                .getPayload();//Retrieves the claims (data) from the token's payload section.
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userName = extractUserName(token);
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token expired: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
