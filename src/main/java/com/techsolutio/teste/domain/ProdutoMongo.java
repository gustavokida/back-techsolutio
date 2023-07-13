package com.techsolutio.teste.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Document("produto")
public class ProdutoMongo {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String nomeProduto;
    private String fornecedor;
    private Double valorProduto;
}
