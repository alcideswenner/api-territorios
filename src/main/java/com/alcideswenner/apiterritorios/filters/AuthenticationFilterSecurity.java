package com.alcideswenner.apiterritorios.filters;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.alcideswenner.apiterritorios.dto.LoginRequestDTO;
import com.alcideswenner.apiterritorios.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilterSecurity extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserRepository userRepository;

    private final String secretyKey;

    public AuthenticationFilterSecurity(AuthenticationManager authenticationManager, UserRepository userRepository, String secretyKey) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.secretyKey = secretyKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String requestBody = "";
        LoginRequestDTO authRequest = null;

        try {
            requestBody = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException("erro", e);
        }

        try {
            authRequest = objectMapper.readValue(requestBody, LoginRequestDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getLogin(),
                authRequest.getPassword());
        setDetails(request, token);

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(secretyKey.getBytes());
        Instant now = Instant.now();

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("validFrom", Date.from(now).toString());
        tokenMap.put("validUntil", Date.from(now.plus(7, ChronoUnit.DAYS)).toString());
        tokenMap.put("teste", Date.from(now.plus(7, ChronoUnit.DAYS)).getTime() + "");
        tokenMap.put("idUser", userRepository.findByUsername(user.getUsername()).getId());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), tokenMap);
    }
}
