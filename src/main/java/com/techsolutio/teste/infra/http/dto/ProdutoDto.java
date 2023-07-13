package com.techsolutio.teste.infra.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProdutoDto {
    private Long id;
    private String idMongo;
    private String nomeProduto;
    private String fornecedor;
    private Double valorProduto;
}
