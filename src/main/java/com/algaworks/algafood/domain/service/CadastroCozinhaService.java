package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoExisteException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

    private static final String COZINHA_EM_USO = "A cozinha de id %d está em uso e não pode ser removida.";
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format(COZINHA_EM_USO, id));
        } catch (EmptyResultDataAccessException exception) {
            throw new CozinhaNaoExisteException(id);
        }
    }

    public Cozinha buscarOuFalhar(Long id) {
        return cozinhaRepository.findById(id).
            orElseThrow(() -> new CozinhaNaoExisteException(id));
    }
}
