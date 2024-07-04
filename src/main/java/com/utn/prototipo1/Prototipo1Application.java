package com.utn.prototipo1;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloCategoriaRepository;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloArticulo.repositories.TipoModeloInventarioRepository;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.repositories.EstadoOrdenDeCompraRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.OrdenDeCompraRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorRepository;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.DetalleFacturaRepository;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class Prototipo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Prototipo1Application.class, args);
		System.out.println("Estoy funcionando");
	}
/*
	@Bean
	CommandLineRunner init(
			ArticuloRepository articuloRepository,
			TipoModeloInventarioRepository tipoModeloInventarioRepository,
			ArticuloCategoriaRepository articuloCategoriaRepository,
			FacturaRepository facturaRepository,
			OrdenDeCompraRepository ordenDeCompraRepository,
			EstadoOrdenDeCompraRepository estadoOrdenDeCompraRepository,
			ProveedorRepository proveedorRepository,
			InventarioRepository inventarioRepository,
			InventarioArticuloRepository inventarioArticuloRepository,
			DetalleFacturaRepository detalleFacturaRepository,
			DemandaRepository demandaRepository
	) {
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

			//Articulos
			Articulo articulo1 = Articulo.builder()
					.nombreArticulo("Televisor LED 55 pulgadas")
					.precioCompra(550)
					.precioVenta(900)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria1)
					.build();

			Articulo articulo2 = Articulo.builder()
					.nombreArticulo("Camisa de vestir blanca")
					.precioCompra(30)
					.precioVenta(60)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria2)
					.build();

			Articulo articulo3 = Articulo.builder()
					.nombreArticulo("Taladro inalámbrico Bosch")
					.precioCompra(120)
					.precioVenta(180)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria3)
					.build();

			Articulo articulo4 = Articulo.builder()
					.nombreArticulo("Silla de oficina ergonómica")
					.precioCompra(80)
					.precioVenta(150)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria1)
					.build();

			Articulo articulo5 = Articulo.builder()
					.nombreArticulo("Zapatillas deportivas Nike")
					.precioCompra(60)
					.precioVenta(100)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria2)
					.build();

			Articulo articulo6 = Articulo.builder()
					.nombreArticulo("Destornillador eléctrico Black & Decker")
					.precioCompra(45)
					.precioVenta(80)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria3)
					.build();

			Articulo articulo7 = Articulo.builder()
					.nombreArticulo("Cafetera automática Philips")
					.precioCompra(70)
					.precioVenta(120)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria1)
					.build();

			Articulo articulo8 = Articulo.builder()
					.nombreArticulo("Jeans azules Levi's")
					.precioCompra(40)
					.precioVenta(80)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria2)
					.build();

			Articulo articulo9 = Articulo.builder()
					.nombreArticulo("Martillo de carpintero Stanley")
					.precioCompra(15)
					.precioVenta(30)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria3)
					.build();

			Articulo articulo10 = Articulo.builder()
					.nombreArticulo("Refrigerador Whirlpool 300L")
					.precioCompra(400)
					.precioVenta(700)
					.fechaBaja(null)
					.articuloCategoria(articuloCategoria1)
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




			//Estado orden de compra
			EstadoOrdenDeCompra estadoOrdenDeCompra1 = EstadoOrdenDeCompra.builder()
					.nombreEOC("Pendiente")
					.fechaBaja(null)
					.build();
			EstadoOrdenDeCompra estadoOrdenDeCompra2 = EstadoOrdenDeCompra.builder()
					.nombreEOC("Confirmado")
					.fechaBaja(null)
					.build();
			EstadoOrdenDeCompra estadoOrdenDeCompra3 = EstadoOrdenDeCompra.builder()
					.nombreEOC("Entregado")
					.fechaBaja(null)
					.build();
			EstadoOrdenDeCompra estadoOrdenDeCompra4 = EstadoOrdenDeCompra.builder()
					.nombreEOC("Cancelado")
					.fechaBaja(null)
					.build();

			estadoOrdenDeCompraRepository.save(estadoOrdenDeCompra1);
			estadoOrdenDeCompraRepository.save(estadoOrdenDeCompra2);
			estadoOrdenDeCompraRepository.save(estadoOrdenDeCompra3);
			estadoOrdenDeCompraRepository.save(estadoOrdenDeCompra4);


			//ProveedorArticulo
			//Ropa
			List<ProveedorArticulo> articulosRopa = Arrays.asList(
					ProveedorArticulo.builder().tiempoDemoraArticulo(5).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo2).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(5).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo5).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(7).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo8).build()
			);
			//Electrodomesticos
			List<ProveedorArticulo> articulosElectrodomesticos = Arrays.asList(
					ProveedorArticulo.builder().tiempoDemoraArticulo(5).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo1).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(3).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo4).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(4).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo7).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(8).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo10).build()
			);
			//Electrodomesticos
			List<ProveedorArticulo> articulosHerramientas = Arrays.asList(
					ProveedorArticulo.builder().tiempoDemoraArticulo(5).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo3).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(3).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo6).build(),
					ProveedorArticulo.builder().tiempoDemoraArticulo(4).costoPedido((float) (Math.random() * 900 + 100)).articulo(articulo9).build()
			);


			//Proveedor
			Proveedor proveedor1 = Proveedor.builder()
					.nombreProveedor("Proveedor de Ropa")
					.fechaBajaProveedor(null)
					.proveedorArticulo(articulosRopa)
					.build();
			Proveedor proveedor2 = Proveedor.builder()
					.nombreProveedor("Proveedor de Electrodomesticos")
					.fechaBajaProveedor(null)
					.proveedorArticulo(articulosElectrodomesticos)
					.build();
			Proveedor proveedor3 = Proveedor.builder()
					.nombreProveedor("Proveedor de Herramientas")
					.fechaBajaProveedor(null)
					.proveedorArticulo(articulosHerramientas)
					.build();

			proveedorRepository.save(proveedor1);
			proveedorRepository.save(proveedor2);
			proveedorRepository.save(proveedor3);

			LocalDate fechainv = LocalDate.of(2024, 1, 1);
			LocalDate fechainv1 = LocalDate.of(2024, 12, 31);

			Inventario inventario = Inventario.builder()
					.fechaDesde(fechainv)
					.fechaHasta(fechainv1)
					.build();
			inventarioRepository.save(inventario);
			Random random = new Random();
			List<InventarioArticulo> inventarioArticulos = Arrays.asList(
					InventarioArticulo.builder().articulo(articulo1).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo2).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo3).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo4).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo5).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo6).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo7).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo8).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo9).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build(),
					InventarioArticulo.builder().articulo(articulo10).inventario(inventario).stockActual(8000).costoAlmacenamiento((int)(30 + random.nextInt(70))).desviacion((int)(1 + random.nextInt(8))).build()
			);
			inventarioArticuloRepository.saveAll(inventarioArticulos);




			Date fechaFactura7 = new GregorianCalendar(2023, Calendar.AUGUST, 20).getTime();
			Date fechaFactura8 = new GregorianCalendar(2020, Calendar.DECEMBER, 16).getTime();
			Date fechaFactura6 = new GregorianCalendar(2018, Calendar.FEBRUARY, 3).getTime();
			Date fechaFactura10 = new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime();
			Date fechaFactura11 = new GregorianCalendar(2021, Calendar.FEBRUARY, 24).getTime();
			Date fechaFactura12 = new GregorianCalendar(2024, Calendar.APRIL, 18).getTime();



			Factura factura6 = Factura.builder().fechaFactura(fechaFactura6).build();
			Factura factura7 = Factura.builder().fechaFactura(fechaFactura7).build();
			Factura factura8 = Factura.builder().fechaFactura(fechaFactura8).build();
			Factura factura10 = Factura.builder().fechaFactura(fechaFactura10).build();
			Factura factura11 = Factura.builder().fechaFactura(fechaFactura11).build();
			Factura factura12 = Factura.builder().fechaFactura(fechaFactura12).build();


			facturaRepository.save(factura6);
			facturaRepository.save(factura7);
			facturaRepository.save(factura8);
			facturaRepository.save(factura10);
			facturaRepository.save(factura11);
			facturaRepository.save(factura12);




			List<Demanda> demandaList = Arrays.asList(
					Demanda.builder().articulo(articulo1).cantidad(1500).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo2).cantidad(1800).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo3).cantidad(1565).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo4).cantidad(1203).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo5).cantidad(1420).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo6).cantidad(1360).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo7).cantidad(1855).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo8).cantidad(1362).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo9).cantidad(1452).periodoYear(2023).build(),
					Demanda.builder().articulo(articulo10).cantidad(1965).periodoYear(2023).build()
			);

			demandaRepository.saveAll(demandaList);
		};

	};*/
}



