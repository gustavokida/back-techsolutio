package com.techsolutio.teste.infra.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EditarProdutoDto {
    private String id;
    private Long idMySql;
    private String nomeProduto;
    private String fornecedor;
    private Double valorProduto;
}
