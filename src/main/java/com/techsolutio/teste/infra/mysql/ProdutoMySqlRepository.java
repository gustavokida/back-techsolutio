package com.techsolutio.teste.infra.mysql;

import com.techsolutio.teste.domain.ProdutoMySql;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoMySqlRepository extends CrudRepository<ProdutoMySql, Long> {
    @Transactional
    void deleteByIdMongo(String id);

    ProdutoMySql findByIdMongo(String id);
}
