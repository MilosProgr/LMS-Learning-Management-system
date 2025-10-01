package ac.rs.singidunum.springBootApp.Security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {
	@Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(encoders.get("bcrypt"));
        return passwordEncoder;
    }

    @Bean
    AuthTokenFilter authTokenFilterBean(AuthenticationConfiguration conf) throws Exception {
        AuthTokenFilter authTokenFilter = new AuthTokenFilter();
        authTokenFilter.setAuthenticationManager(conf.getAuthenticationManager());
        return authTokenFilter;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http, AuthenticationConfiguration conf) throws Exception {
        return http.csrf(csrf -> csrf.disable())
        		 .cors(cors -> cors.disable()) // Ako se koristi globalni CORS filter, ovde je iskljucen
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authTokenFilterBean(conf), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
