package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoExisteException extends NegocioException{
    
    public EntidadeNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
