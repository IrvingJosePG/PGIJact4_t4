package com.web.actividad4.controller;

import com.web.actividad4.dto.AuthRespuestaDTO;
import com.web.actividad4.dto.LoginDTO;
import com.web.actividad4.dto.RegistroDTO;
import com.web.actividad4.entity.Usuario;
import com.web.actividad4.repository.UsuarioRepository;
import com.web.actividad4.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // 1. ENDPOINT DE REGISTRO
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroDTO registroDTO) {
        // Validamos si el email ya existe en la BD
        if (usuarioRepository.findByEmail(registroDTO.getEmail()).isPresent()) {
            return new ResponseEntity<>("El email ya está registrado", HttpStatus.BAD_REQUEST);
        }

        // Creamos el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setEmail(registroDTO.getEmail());
        // ENCRIPTAMOS la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }

    // 2. ENDPOINT DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthRespuestaDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        
        // Spring Security verifica si el correo y la contraseña (encriptada) coinciden
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        // Si es correcto, lo marcamos como logueado en la memoria de Spring
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generamos el Token JWT usando la clase que creamos
        String token = jwtUtil.generateToken(authentication.getName());

        // Devolvemos el token al usuario en formato JSON
        return new ResponseEntity<>(new AuthRespuestaDTO(token), HttpStatus.OK);
    }
}