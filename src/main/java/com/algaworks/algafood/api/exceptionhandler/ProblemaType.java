package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemaType {
    ENTIDADE_NAO_EXISTE("/entidade-nao-existe", "Entidade não existe."),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
    NEGOCIO("/negocio", "Exceção de negócio.");

    private String type;
    private String title;

    ProblemaType(String path, String title) {
        this.type = "https://algafood.com.br" + path;
        this.title = title;
    }
}
