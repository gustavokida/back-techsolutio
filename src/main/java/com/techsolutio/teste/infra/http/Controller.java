package com.techsolutio.teste.infra.http;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.domain.ProdutoMySql;
import com.techsolutio.teste.domain.Usuario;
import com.techsolutio.teste.infra.http.dto.*;
import com.techsolutio.teste.infra.mongo.ProdutoMongoRepository;
import com.techsolutio.teste.infra.mongo.UsuarioRepository;
import com.techsolutio.teste.infra.mysql.ProdutoMySqlRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {
    private final UsuarioRepository usuarioRepository;
    private final ProdutoMongoRepository produtoMongoRepository;
    private final ProdutoMySqlRepository produtoMySqlRepository;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto) throws LoginException {
        var usuario = buscaUsuario(loginRequestDto);
        var retorno = LoginResponse.builder()
                .id(usuario.getId())
                .build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(LogoutResponse.builder()
                    .message("Deslogado com sucesso")
                    .build());
    }
    @PostMapping("/criar-usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDto usuarioDto){
        var usuario = Usuario.builder()
                .password(usuarioDto.getPassword())
                .username(usuarioDto.getUsername())
                .build();
        var retorno = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @PostMapping("/cadastro")
    public ResponseEntity<CadastroResponse> cadastro(@RequestBody ProdutoDto produtoDto){
        var produtoMongo = salvarProdutoMongo(produtoDto);
        salvarProdutoMySql(produtoDto, produtoMongo);

        var retorno = CadastroResponse.builder()
                .id(produtoMongo.getId())
                .build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @GetMapping("/cadastro")
    public ResponseEntity<List<ProdutoMongo>> buscarProdutos(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoMongoRepository.findAll());
    }
    @PutMapping("/cadastro")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProdutoMongo> editarProduto(@RequestBody ProdutoDto produtoDto){
        var produto = ProdutoMongo.builder()
                .id(produtoDto.getIdMongo())
                .nomeProduto(produtoDto.getNomeProduto())
                .fornecedor(produtoDto.getFornecedor())
                .valorProduto(produtoDto.getValorProduto())
                .build();
        var retorno = produtoMongoRepository.save(produto);
        var produtoMySql = ProdutoMySql.builder()
                    .id(produtoDto.getId())
                    .idMongo(produtoDto.getIdMongo())
                    .nomeProduto(produtoDto.getNomeProduto())
                    .valorProduto(produtoDto.getValorProduto())
                    .fornecedor(produtoDto.getFornecedor())
                    .build();
        produtoMySqlRepository.save(produtoMySql);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @DeleteMapping("/cadastro/{idMongo}")
    public ResponseEntity<DeleteResponse> removerProduto(@PathVariable(value = "idMongo") final String id){
        produtoMongoRepository.deleteById(id);
        produtoMySqlRepository.deleteByIdMongo(id);
        var retorno = DeleteResponse.builder()
                .message("Produto com id " + id + "deletado com sucesso!")
                .build();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }

    private Usuario buscaUsuario(LoginRequestDto loginRequestDto) throws LoginException {
        return usuarioRepository
                .findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword())
                .orElseThrow(LoginException::new);
    }

    private ProdutoMongo salvarProdutoMongo(ProdutoDto produtoDto){
        var produto = ProdutoMongo.builder()
                .nomeProduto(produtoDto.getNomeProduto())
                .fornecedor(produtoDto.getFornecedor())
                .valorProduto(produtoDto.getValorProduto())
                .build();
        return produtoMongoRepository.save(produto);
    }
    private void salvarProdutoMySql(ProdutoDto produtoDto, ProdutoMongo produtoMongo){
        var produto = ProdutoMySql.builder()
                .idMongo(produtoMongo.getId())
                .nomeProduto(produtoDto.getNomeProduto())
                .fornecedor(produtoDto.getFornecedor())
                .valorProduto(produtoDto.getValorProduto())
                .build();
        produtoMySqlRepository.save(produto);
    }

}
