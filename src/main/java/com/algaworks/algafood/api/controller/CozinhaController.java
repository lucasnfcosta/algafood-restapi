package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoExisteException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long id, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

        if (cozinhaAtual.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            
            Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
        }

        return ResponseEntity.notFound().build();
    }

    // @DeleteMapping("/{cozinhaId}")
    // public ResponseEntity<Cozinha> remover(@PathVariable("cozinhaId") Long id) {
    //     try {
    //         cadastroCozinha.remover(id);
    //         return ResponseEntity.noContent().build();
    //     } catch(EntidadeEmUsoException exception) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).build();
    //     } catch (EntidadeNaoExisteException exception){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }
    // }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("cozinhaId") Long id) {
        try {
            cadastroCozinha.remover(id);
        } catch (EntidadeNaoExisteException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
