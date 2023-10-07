package hu.pcvilag.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.pcvilag.app.models.JwtRequest;
import hu.pcvilag.app.models.JwtResponse;
import hu.pcvilag.app.service.CustomUserDetailsService;
import hu.pcvilag.app.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTAuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        logger.info("LOGIN: {} ", userDetails);
        logger.info("TOKEN: {} ", token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}