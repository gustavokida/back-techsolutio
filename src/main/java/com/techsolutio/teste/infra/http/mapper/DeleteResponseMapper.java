package com.techsolutio.teste.infra.http.mapper;

import com.techsolutio.teste.infra.http.dto.DeleteResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteResponseMapper {
    public DeleteResponse map(String id){
        return DeleteResponse.builder()
                .message("Produto com id " + id + "deletado com sucesso!")
                .build();
    }
}
