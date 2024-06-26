package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloCategoriaRepository;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloArticulo.repositories.TipoModeloInventarioRepository;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class Prototipo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Prototipo1Application.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(TipoModeloInventarioRepository tipoModeloInventarioRepository, ArticuloCategoriaRepository articuloCategoriaRepository) {
		return args -> {

						TipoModeloInventario tipoModeloInventario1= TipoModeloInventario.builder()
					.nombre("Lote fijo")
					.build();

			TipoModeloInventario tipoModeloInventario2= TipoModeloInventario.builder()
					.nombre("Intervalo fijo")
					.build();

			tipoModeloInventarioRepository.save(tipoModeloInventario1);
			tipoModeloInventarioRepository.save(tipoModeloInventario2);


		ArticuloCategoria articuloCategoria1 = ArticuloCategoria.builder()
				.nombreCategoria("Electrodomesticos")
				.tipoModeloInventario(tipoModeloInventario1)
				.build();

			ArticuloCategoria articuloCategoria2 = ArticuloCategoria.builder()
					.nombreCategoria("Ropa")
					.tipoModeloInventario(tipoModeloInventario2)
					.build();

			ArticuloCategoria articuloCategoria3 = ArticuloCategoria.builder()
					.nombreCategoria("Herramientas")
					.tipoModeloInventario(tipoModeloInventario2)
					.build();
			articuloCategoriaRepository.save(articuloCategoria1);
			articuloCategoriaRepository.save(articuloCategoria2);
			articuloCategoriaRepository.save(articuloCategoria3);
	};
}

}



