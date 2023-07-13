package com.techsolutio.teste.service;

import com.techsolutio.teste.domain.Usuario;
import com.techsolutio.teste.infra.mongo.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@AllArgsConstructor
public class UsuarioMongoService {
    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuario(Usuario usuario) throws LoginException {
        return usuarioRepository
                .findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword())
                .orElseThrow(LoginException::new);
    }

}
