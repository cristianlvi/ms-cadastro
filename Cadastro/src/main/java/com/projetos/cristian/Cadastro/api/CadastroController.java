package com.projetos.cristian.Cadastro.api;

import com.projetos.cristian.Cadastro.dto.FuncionarioDto;
import com.projetos.cristian.Cadastro.service.CadastroService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(name = "/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;


    @GetMapping(value = "/funcionario/{id}")
    public ResponseEntity<FuncionarioDto> detalharFuncionario(@PathVariable @NotNull Long id) {
        FuncionarioDto funcionario = cadastroService.obterFuncionarioId(id);

        return ResponseEntity.ok(funcionario);
    }


    @PostMapping(value = "/funcionario")
    public ResponseEntity<FuncionarioDto> saveFuncionario(@RequestBody FuncionarioDto dto, UriComponentsBuilder uriBuilder) {
        FuncionarioDto funcionario = cadastroService.saveFuncionario(dto);

        URI endereco = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(endereco).body(funcionario);
    }


}