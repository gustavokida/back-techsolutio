package com.techsolutio.teste.infra.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CriarUsuarioDto {
    private String username;
    private String password;
}
