package com.techsolutio.teste.infra.http;

import com.techsolutio.teste.domain.ProdutoMongo;
import com.techsolutio.teste.domain.ProdutoMySql;
import com.techsolutio.teste.infra.http.dto.*;
import com.techsolutio.teste.infra.http.mapper.*;
import com.techsolutio.teste.infra.mongo.ProdutoMongoRepository;
import com.techsolutio.teste.infra.mysql.ProdutoMySqlRepository;
import com.techsolutio.teste.service.ProdutoMongoService;
import com.techsolutio.teste.service.ProdutoMySqlService;
import com.techsolutio.teste.service.UsuarioMongoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class Controller {
    private final UsuarioMongoService usuarioMongoService;
    private final ProdutoMongoService produtoMongoService;
    private final ProdutoMySqlService produtoMySqlService;
    private final CriarUsuarioMapper criarUsuarioMapper;
    private final LoginRequestMapper loginRequestMapper;
    private final LoginResponseMapper loginResponseMapper;
    private final CadastrarProdutoMapper cadastrarProdutoMapper;
    private final CadastroResponseMapper cadastroResponseMapper;
    private final EditarProdutoMapper editarProdutoMapper;
    private final DeleteResponseMapper deleteResponseMapper;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto) throws LoginException {
        var usuarioMapped = loginRequestMapper.map(loginRequestDto);
        var usuario = usuarioMongoService.buscarUsuario(usuarioMapped);
        var retorno = loginResponseMapper.map(usuario);
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
    public ResponseEntity<LoginResponse> criarUsuario(@RequestBody CriarUsuarioDto criarUsuarioDto){
        var usuario = criarUsuarioMapper.map(criarUsuarioDto);
        var retornoNovoUsuario = usuarioMongoService.salvarUsuario(usuario);
        var retorno = loginResponseMapper.map(retornoNovoUsuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @PostMapping("/cadastro")
    public ResponseEntity<CadastroResponse> cadastro(@RequestBody CadastrarProdutoDto cadastrarProdutoDto){
        var produtoMongoMapped = cadastrarProdutoMapper.mapMongo(cadastrarProdutoDto);
        var produtoMongo = produtoMongoService.salvarProdutoMongo(produtoMongoMapped);
        var produtoMySqlMapped = cadastrarProdutoMapper.mapMySql(produtoMongo);
        produtoMySqlService.salvarProdutoMySql(produtoMySqlMapped);

        var retorno = cadastroResponseMapper.map(produtoMongo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @GetMapping("/cadastro")
    public ResponseEntity<List<ProdutoMongo>> buscarProdutos(){
        var produtos = produtoMongoService.buscarTodosProdutos();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtos);
    }
    @PutMapping("/cadastro")
    public ResponseEntity<ProdutoMongo> editarProduto(@RequestBody EditarProdutoDto editarProdutoDto){
        var produto = editarProdutoMapper.mapMongo(editarProdutoDto);
        var retorno = produtoMongoService.editarProdutoMongo(produto);
        var produtoMySql = editarProdutoMapper.mapMySql(editarProdutoDto);
        produtoMySqlService.editarProdutoMySql(produtoMySql);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }
    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<DeleteResponse> removerProduto(@PathVariable(value = "id") final String id){
        produtoMongoService.deletarProduto(id);
        produtoMySqlService.deletarProduto(id);
        var retorno = deleteResponseMapper.map(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }

}
