package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.domain.ProdutoMySql;
import com.techsolutio.teste.infra.http.dto.EditarProdutoDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditarProdutoMapper {
    public ProdutoMongo mapMongo(EditarProdutoDto editarProdutoDto){
        return ProdutoMongo.builder()
                .id(editarProdutoDto.getId())
                .nomeProduto(editarProdutoDto.getNomeProduto())
                .fornecedor(editarProdutoDto.getFornecedor())
                .valorProduto(editarProdutoDto.getValorProduto())
                .build();
    }
    public ProdutoMySql mapMySql(EditarProdutoDto editarProdutoDto){
        return ProdutoMySql.builder()
                .idMongo(editarProdutoDto.getId())
                .nomeProduto(editarProdutoDto.getNomeProduto())
                .valorProduto(editarProdutoDto.getValorProduto())
                .fornecedor(editarProdutoDto.getFornecedor())
                .build();
    }
}
