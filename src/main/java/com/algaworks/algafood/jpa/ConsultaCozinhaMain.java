package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        // Cozinha cozinha1 = new Cozinha();
        // cozinha1.setNome("Brasileira");
        // Cozinha cozinha2 = new Cozinha();
        // cozinha2.setNome("Vegana");

        // cadastroCozinha.salvar(cozinha1);
        // cadastroCozinha.salvar(cozinha2);

        // List<Cozinha> cozinhas = cadastroCozinha.listar();
        // for (Cozinha cozinha : cozinhas) {
        //     System.out.println(cozinha.getNome());
        // }

        // Cozinha buscaCozinha = cadastroCozinha.buscar(1L);

        // System.out.println(buscaCozinha.getNome());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Peruana");

        cadastroCozinha.salvar(cozinha);
        cadastroCozinha.remover(cozinha);

    }
}
