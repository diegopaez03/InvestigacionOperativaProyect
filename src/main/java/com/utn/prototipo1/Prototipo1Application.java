package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
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

	/*@Bean
	CommandLineRunner init(FacturaRepository facturaRepository) {
		return args -> {

			Date fechaFactura1 = new GregorianCalendar(2024, Calendar.JANUARY, 19).getTime();
			Date fechaFactura4 = new GregorianCalendar(2024, Calendar.FEBRUARY, 22).getTime();
			Date fechaFactura3 = new GregorianCalendar(2023, Calendar.DECEMBER, 15).getTime();
			Date fechaFactura5 = new GregorianCalendar(2023, Calendar.MARCH, 10).getTime();
			Date fechaFactura7 = new GregorianCalendar(2023, Calendar.AUGUST, 20).getTime();
			Date fechaFactura8 = new GregorianCalendar(2020, Calendar.DECEMBER, 16).getTime();
			Date fechaFactura10 = new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime();
			Date fechaFactura100 = new GregorianCalendar(2022, Calendar.AUGUST, 28).getTime();
			Date fechaFactura11 = new GregorianCalendar(2021, Calendar.FEBRUARY, 24).getTime();
			Date fechaFactura6 = new GregorianCalendar(2018, Calendar.FEBRUARY, 3).getTime();
			Date fechaFactura9 = new GregorianCalendar(2018, Calendar.AUGUST, 21).getTime();


			Factura factura1 = Factura.builder().fechaFactura(fechaFactura9).build();
			Factura factura2 = Factura.builder().fechaFactura(fechaFactura6).build();
			Factura factura3 = Factura.builder().fechaFactura(fechaFactura8).build();
			Factura factura4 = Factura.builder().fechaFactura(fechaFactura11).build();
			Factura factura6 = Factura.builder().fechaFactura(fechaFactura10).build();
			Factura factura7 = Factura.builder().fechaFactura(fechaFactura100).build();
			Factura factura8 = Factura.builder().fechaFactura(fechaFactura7).build();
			Factura factura9 = Factura.builder().fechaFactura(fechaFactura5).build();
			Factura factura10 = Factura.builder().fechaFactura(fechaFactura3).build();
			Factura factura11 = Factura.builder().fechaFactura(fechaFactura4).build();
			Factura factura12 = Factura.builder().fechaFactura(fechaFactura1).build();

			facturaRepository.save(factura1);
			facturaRepository.save(factura2);
			facturaRepository.save(factura3);
			facturaRepository.save(factura4);
			facturaRepository.save(factura6);
			facturaRepository.save(factura7);
			facturaRepository.save(factura8);
			facturaRepository.save(factura9);
			facturaRepository.save(factura10);
			facturaRepository.save(factura11);
			facturaRepository.save(factura12);
	};
}*/

}



