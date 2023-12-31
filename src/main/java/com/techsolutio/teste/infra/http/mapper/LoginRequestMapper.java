package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.domain.Usuario;
import com.techsolutio.teste.infra.http.dto.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginRequestMapper {
    public Usuario map(LoginRequestDto loginRequestDto){
        return Usuario.builder()
                .password(loginRequestDto.getPassword())
                .username(loginRequestDto.getUsername())
                .build();
    }
}
