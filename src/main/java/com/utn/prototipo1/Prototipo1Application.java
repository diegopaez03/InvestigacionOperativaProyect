package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
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

	@Bean
	CommandLineRunner init(ArticuloRepository articuloRepository) {
		return args -> {

		Articulo articulo1 = Articulo.builder()
				.nombreArticulo("Televisor LED 55 pulgadas")
				.precioCompra(550)
				.precioVenta(900)
				.fechaBaja(null)
				.build();

		Articulo articulo2 = Articulo.builder()
				.nombreArticulo("Camisa de vestir blanca")
				.precioCompra(30)
				.precioVenta(60)
				.fechaBaja(null)
				.build();

		Articulo articulo3 = Articulo.builder()
				.nombreArticulo("Taladro inalámbrico Bosch")
				.precioCompra(120)
				.precioVenta(180)
				.fechaBaja(null)
				.build();

		Articulo articulo4 = Articulo.builder()
				.nombreArticulo("Silla de oficina ergonómica")
				.precioCompra(80)
				.precioVenta(150)
				.fechaBaja(null)
				.build();

		Articulo articulo5 = Articulo.builder()
				.nombreArticulo("Zapatillas deportivas Nike")
				.precioCompra(60)
				.precioVenta(100)
				.fechaBaja(null)
				.build();

		Articulo articulo6 = Articulo.builder()
				.nombreArticulo("Destornillador eléctrico Black & Decker")
				.precioCompra(45)
				.precioVenta(80)
				.fechaBaja(null)
				.build();

		Articulo articulo7 = Articulo.builder()
				.nombreArticulo("Cafetera automática Philips")
				.precioCompra(70)
				.precioVenta(120)
				.fechaBaja(null)
				.build();

		Articulo articulo8 = Articulo.builder()
				.nombreArticulo("Jeans azules Levi's")
				.precioCompra(40)
				.precioVenta(80)
				.fechaBaja(null)
				.build();

		Articulo articulo9 = Articulo.builder()
				.nombreArticulo("Martillo de carpintero Stanley")
				.precioCompra(15)
				.precioVenta(30)
				.fechaBaja(null)
				.build();

		Articulo articulo10 = Articulo.builder()
				.nombreArticulo("Refrigerador Whirlpool 300L")
				.precioCompra(400)
				.precioVenta(700)
				.fechaBaja(null)
				.build();

		// Guardar los artículos en la base de datos
		articuloRepository.save(articulo1);
		articuloRepository.save(articulo2);
		articuloRepository.save(articulo3);
		articuloRepository.save(articulo4);
		articuloRepository.save(articulo5);
		articuloRepository.save(articulo6);
		articuloRepository.save(articulo7);
		articuloRepository.save(articulo8);
		articuloRepository.save(articulo9);
		articuloRepository.save(articulo10);
	};
}
}



