package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.domain.Usuario;
import com.techsolutio.teste.infra.http.dto.LoginResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponseMapper {
    public LoginResponse map(Usuario usuario){
        return LoginResponse.builder()
                .id(usuario.getId())
                .build();
    }
}
