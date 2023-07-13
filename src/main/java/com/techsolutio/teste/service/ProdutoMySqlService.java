package com.techsolutio.teste.service;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.domain.ProdutoMySql;
import com.techsolutio.teste.infra.mysql.ProdutoMySqlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoMySqlService {
    private final ProdutoMySqlRepository produtoMySqlRepository;
    public void salvarProdutoMySql(ProdutoMySql produtoMySql){
        produtoMySqlRepository.save(produtoMySql);
    }
    public void editarProdutoMySql(ProdutoMySql produtoMySql){
        var antigoProdutoMySql = produtoMySqlRepository.findByIdMongo(produtoMySql.getIdMongo());
        produtoMySql.setId(antigoProdutoMySql.getId());
        produtoMySqlRepository.save(produtoMySql);
    }
    public void deletarProduto(String id){
        produtoMySqlRepository.deleteByIdMongo(id);
    }
}
