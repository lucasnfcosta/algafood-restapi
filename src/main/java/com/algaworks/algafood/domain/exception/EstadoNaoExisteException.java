package com.algaworks.algafood.domain.exception;

public class EstadoNaoExisteException extends EntidadeNaoExisteException {

    public EstadoNaoExisteException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoExisteException (Long id) {
        this(String.format("O estado de id %d não existe", id));
    }
    
}
