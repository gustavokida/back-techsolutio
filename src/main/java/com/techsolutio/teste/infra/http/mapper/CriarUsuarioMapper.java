package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.domain.Usuario;
import com.techsolutio.teste.infra.http.dto.CriarUsuarioDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CriarUsuarioMapper {
    public Usuario map(CriarUsuarioDto criarUsuarioDto){
        return Usuario.builder()
                .password(criarUsuarioDto.getPassword())
                .username(criarUsuarioDto.getUsername())
                .build();
    }
}
