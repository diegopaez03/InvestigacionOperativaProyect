package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.DetalleFacturaRepository;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Prototipo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Prototipo1Application.class, args);
		System.out.println("Estoy funcionando");
	}
	/*@Autowired
	ArticuloRepository articuloRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	DetalleFacturaRepository detalleFacturaRepository;

	@Bean
	CommandLineRunner init(ArticuloRepository ArticuloRepository)	{
		return args -> {

			Articulo articulo1 = Articulo.builder()
					.nombreArticulo("Tornillo")
					.precioCompra(600)
					.precioVenta(10)
					.build();

			Articulo articulo2 = Articulo.builder()
					.nombreArticulo("Clavo")
					.precioCompra(400)
					.precioVenta(20)
					.build();

			articuloRepository.save(articulo1);
			articuloRepository.save(articulo2);


			/*-Factura factura1 = Factura.builder()
					.fechaFactura(new Date())
					.build();

			Factura factura2 = Factura.builder()
					.fechaFactura(new Date())
					.build();

			facturaRepository.save(factura1);
			facturaRepository.save(factura2);
			DetalleFactura detalleFactura1 = DetalleFactura.builder()
					.linea(2)
					.cantidad(3)
					.articulo(articulo1)
					.factura(factura1)
					.build();
			detalleFacturaRepository.save(detalleFactura1);*/



		//};

	//}
}
