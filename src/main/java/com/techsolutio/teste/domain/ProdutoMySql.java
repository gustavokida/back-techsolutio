package com.techsolutio.teste.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "produto")
public class ProdutoMySql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idmongo")
    private String idMongo;
    @Column(name = "nomeproduto")
    private String nomeProduto;
    @Column(name = "fornecedor")
    private String fornecedor;
    @Column(name = "valorproduto")
    private Double valorProduto;
}
