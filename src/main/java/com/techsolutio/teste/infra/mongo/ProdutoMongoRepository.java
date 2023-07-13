package com.techsolutio.teste.infra.mongo;

import com.techsolutio.teste.domain.ProdutoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoMongoRepository extends MongoRepository<ProdutoMongo, Long> {
    void deleteById(String id);
}
