package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloCategoriaRepository;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloArticulo.repositories.TipoModeloInventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.repositories.EstadoOrdenDeCompraRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.OrdenDeCompraRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorRepository;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootApplication
public class Prototipo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Prototipo1Application.class, args);
		System.out.println("Estoy funcionando");
	}
    /*@Bean
	CommandLineRunner init(ArticuloRepository articuloRepository, TipoModeloInventarioRepository tipoModeloInventarioRepository,
						   ArticuloCategoriaRepository articuloCategoriaRepository, FacturaRepository facturaRepository) {
        return args -> {

            Date fechaFactura8 = new GregorianCalendar(2020, Calendar.DECEMBER, 16).getTime();
            Date fechaFactura6 = new GregorianCalendar(2018, Calendar.FEBRUARY, 3).getTime();

            Factura factura8 = Factura.builder().fechaFactura(fechaFactura8).build();
            Factura factura6 = Factura.builder().fechaFactura(fechaFactura6).build();

            facturaRepository.save(factura6);
            facturaRepository.save(factura8);

        };
    }*/


}



