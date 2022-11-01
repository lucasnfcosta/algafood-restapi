package com.algaworks.algafood.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {
    public static void main(String[] args) {
        
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    
            RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);
    
            // Restaurante restaurante1 = new Restaurante();
            // restaurante1.setNome("Brasileira");
            // Restaurante restuarante2 = new Restaurante();
            // restuarante2.setNome("Vegana");
    
            // cadastroRestaurante.salvar(restaurante1);
            // cadastroRestaurante.salvar(restuarante2);
    
            List<Restaurante> restaurantes = cadastroRestaurante.listar();
            for (Restaurante restaurante : restaurantes) {
                System.out.println(restaurante.getNome());
                System.out.println(restaurante.getTaxaFrete());
                System.out.println(restaurante.getCozinha().getNome());
            }
    
            // Cozinha buscaCozinha = cadastroCozinha.buscar(1L);
    
            // System.out.println(buscaCozinha.getNome());
    
            // Restaurante restaurante = new Restaurante();
            // restaurante.setId(1L);
            // restaurante.setNome("Peruana");
    
            // cadastroRestaurante.salvar(restaurante);
            // cadastroRestaurante.remover(restaurante);
    }
}
