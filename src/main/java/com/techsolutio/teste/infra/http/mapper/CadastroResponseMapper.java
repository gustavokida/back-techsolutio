package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.infra.http.dto.CadastroResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CadastroResponseMapper {
    public CadastroResponse map(ProdutoMongo produtoMongo){
        return CadastroResponse.builder()
                .id(produtoMongo.getId())
                .build();
    }
}
