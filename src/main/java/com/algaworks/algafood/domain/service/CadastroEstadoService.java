package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoExisteException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void remover(Long id) {
        try {
            estadoRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoExisteException(String.format("O estado de id %d não existe", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                "O estado de id %d não pode ser removido pois está em uso", id));
        }
    }
}