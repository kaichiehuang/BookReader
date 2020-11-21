package com.group5.BookRead.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private static final String SECRET_KEY = "secret";
    private static final int EXPIRATION_PERIOD
            = 1000 * 60 * 60 * 10;
    /**  extract username from jwt token
     * @param token
     * @return username
     */
    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }
    /**  extract expiration date from jwt token
     * @param token
     * @return expiration date
     */
    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    /**  extract claim from jwt token
     * @param token
     * @param claimsResolver
     * @return claim
     */
    public <T> T extractClaim(final String token,
                              final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(final String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }
    /**  Generate jwt token
     * @param userDetails
     * @return jwt token
     */
    public String generateToken(final UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    /**  Create jwt token
     * @param claims
     * @param subject
     * @return jwt token
     */
    private String createToken(final Map<String,
            Object> claims, final String subject) {

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + EXPIRATION_PERIOD))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    /**  Validate jwt token
     * @param token
     * @param userDetails
     * @return validity of token
     */
    public Boolean validateToken(final String token,
                                 final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }
}
