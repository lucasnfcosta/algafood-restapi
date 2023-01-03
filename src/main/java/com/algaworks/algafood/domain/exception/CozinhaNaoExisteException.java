package com.algaworks.algafood.domain.exception;

public class CozinhaNaoExisteException extends EntidadeNaoExisteException {

    public CozinhaNaoExisteException(String mensagem) {
        super(mensagem);
    }
    
    public CozinhaNaoExisteException (Long id) {
        this(String.format("A cozinha de id %d não existe", id));
    }
}
