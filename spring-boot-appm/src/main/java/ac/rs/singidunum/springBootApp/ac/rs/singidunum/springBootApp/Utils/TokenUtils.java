package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenUtils {

    @Value("${token.secret}")
    private String secret;
    
    @Value("${token.expiration}")
    private Long expiration;
    
    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            //System.err.println("Error parsing token: " + e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }

    private boolean isExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            //System.err.println("Error checking token expiration: " + e.getMessage());
            //e.printStackTrace();
        }
        return true;
    }

    public String getUsername(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (Exception e) {
            //System.err.println("Error getting username from token: " + e.getMessage());
           // e.printStackTrace();
        }
        return null;
    }

    public List<String> getRoles(String token) {
        try {
            return (List<String>) getClaims(token).get("roles");
        } catch (Exception e) {
            System.err.println("Error getting roles from token: " + e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        if (userDetails == null) {
            return false; // or handle the case appropriately
        }
        String username = getUsername(token);
       // List<String> roles = getRoles(token);
        return username != null && username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    public String generateToken(UserDetails userDetails) {
        // Kastujemo UserDetails na CustomUserDetails da bismo pristupili ID-u
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", customUserDetails.getUsername());  // Korisničko ime
        claims.put("id", customUserDetails.getId());         // ID korisnika
        claims.put("created", new Date());
        claims.put("roles", customUserDetails.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .toList());     // Uloge

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getKey())
                .compact();
    }
}
