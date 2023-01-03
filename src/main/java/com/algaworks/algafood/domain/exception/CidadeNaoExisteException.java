package com.algaworks.algafood.domain.exception;

public class CidadeNaoExisteException extends EntidadeNaoExisteException{

    public CidadeNaoExisteException(String mensagem) {
        super(mensagem);
    }
    
    public CidadeNaoExisteException (Long id) {
        this(String.format("A cidade de id %d não existe", id));
    }
}
