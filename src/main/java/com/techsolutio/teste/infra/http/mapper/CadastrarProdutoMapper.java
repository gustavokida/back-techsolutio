package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.domain.ProdutoMySql;
import com.techsolutio.teste.infra.http.dto.CadastrarProdutoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CadastrarProdutoMapper {
    public ProdutoMongo mapMongo(CadastrarProdutoDto cadastrarProdutoDto){
        return ProdutoMongo.builder()
                .nomeProduto(cadastrarProdutoDto.getNomeProduto())
                .fornecedor(cadastrarProdutoDto.getFornecedor())
                .valorProduto(cadastrarProdutoDto.getValorProduto())
                .build();
    }
    public ProdutoMySql mapMySql(ProdutoMongo produtoMongo){
        return ProdutoMySql.builder()
                .idMongo(produtoMongo.getId())
                .nomeProduto(produtoMongo.getNomeProduto())
                .fornecedor(produtoMongo.getFornecedor())
                .valorProduto(produtoMongo.getValorProduto())
                .build();
    }
}
