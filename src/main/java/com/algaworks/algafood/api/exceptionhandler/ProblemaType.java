package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemaType {
    ENTIDADE_NAO_EXISTE("/entidade-nao-existe", "Entidade não existe.");

    private String type;
    private String title;

    ProblemaType(String path, String title) {
        this.type = "https://algafood.com.br" + path;
        this.title = title;
    }
}
