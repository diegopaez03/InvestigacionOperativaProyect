package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Prototipo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Prototipo1Application.class, args);
		System.out.println("Estoy funcionando");
	}
	@Autowired
	ArticuloRepository articuloRepository;

	@Bean
	CommandLineRunner init(ArticuloRepository ArticuloRepository)	{
		return args -> {

			Articulo articulo1 = Articulo.builder()
					.nombreArticulo("Tornillo")
					.precio(900)
					.build();

			Articulo articulo2 = Articulo.builder()
					.nombreArticulo("Clavo")
					.precio(600)
					.build();

			articuloRepository.save(articulo1);
			articuloRepository.save(articulo2);


		};

	}
}
