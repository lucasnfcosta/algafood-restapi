package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoExisteException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.remover(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format("A cozinha de id %d está em uso e não pode ser removida.", id));
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoExisteException(String.format("A cozinha de id %d não existe.", id));
        }
    }
}
