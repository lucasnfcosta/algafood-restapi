package com.algaworks.algafood.domain.exception;

public class RestauranteNaoExisteException extends EntidadeNaoExisteException{

    public RestauranteNaoExisteException(String mensagem) {
        super(mensagem);
    }
    
    public RestauranteNaoExisteException (Long id) {
        this(String.format("O restaurante de id %d não existe", id));
    }
}
