package com.techsolutio.teste.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document("usuario")
public class Usuario {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
}
