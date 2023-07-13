package com.techsolutio.teste.service;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.infra.mongo.ProdutoMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoMongoService {
    private final ProdutoMongoRepository produtoMongoRepository;
    public ProdutoMongo salvarProdutoMongo(ProdutoMongo produtoMongo){
        return produtoMongoRepository.save(produtoMongo);
    }
    public List<ProdutoMongo> buscarTodosProdutos(){
        return produtoMongoRepository.findAll();
    }
    public ProdutoMongo editarProdutoMongo(ProdutoMongo produtoMongo){
        return produtoMongoRepository.save(produtoMongo);
    }

    public void deletarProduto(String id){
        produtoMongoRepository.deleteById(id);
    }
}
