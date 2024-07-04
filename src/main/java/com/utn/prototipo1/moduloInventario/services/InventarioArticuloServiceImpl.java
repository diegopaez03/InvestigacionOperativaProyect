package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.exceptions.StockInsuficienteException;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class InventarioArticuloServiceImpl implements InventarioArticuloService {

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private DemandaRepository demandaRepository;
    @Autowired
    private InventarioServices inventarioServices;

    @Override
    public List<InventarioArticulo> findAll() {
        return inventarioArticuloRepository.findAll();
    }

    @Override
    public InventarioArticulo findById(Long id) {
        return inventarioArticuloRepository.findById(id).orElse(null);
    }

    @Override
    public InventarioArticulo save(InventarioArticulo inventarioArticulo) {
        return inventarioArticuloRepository.save(inventarioArticulo);
    }

    @Override
    public List<InventarioArticulo> obtenerArticuloPorInventario(Long inventarioId) {
        return inventarioArticuloRepository.findByInventarioId(inventarioId);
    }

    @Override
    public void actualizarStockPorFactura(Factura factura) {
        for (DetalleFactura detalle : factura.getDetalleFacturas()) {
            restarStock(detalle.getArticulo(), detalle.getCantidad());
        }
    }
    @Override
    public Inventario crearInventario(){
        Inventario nuevoInventario = new Inventario();
        LocalDate fechaActual = LocalDate.now();
        nuevoInventario.setFechaDesde(fechaActual);
        nuevoInventario.setFechaHasta(fechaActual.plusDays(30));
        inventarioRepository.save(nuevoInventario);
        return nuevoInventario;
    }
    @Override
    public List<InventarioArticulo> obtenerInventarioArticulosConStockBajo() {
        return inventarioArticuloRepository.findAllByStockActualLessThanOrEqualToStockSeguridad();
    }
    @Override
    public List<InventarioArticulo> obtenerInventarioArticulosDebajoPuntoPedido() {
        return inventarioArticuloRepository.findAllByStockActualLessThanOrEqualToPuntoPedido();
    }

    @Override
    @Transactional
    public void sumarStock(Articulo articulo, double cantidad) {
        // Obtener el inventario actual
        Inventario inventario = inventarioServices.obtenerUltimoInventario();

        // Verificar si el inventario para hoy existe, si no, crearlo
        if (inventario == null) {
            inventario = crearInventario();
        }

        // Obtener el InventarioArticulo específico para el artículo y el inventario actual
        List<InventarioArticulo> inventarioArticulos = inventarioArticuloRepository.findByArticuloAndInventario(articulo, inventario);


        if (inventarioArticulos.isEmpty()) {  // Si no se encontró, crear un nuevo InventarioArticulo y asociarlo al inventario actual

            InventarioArticulo nuevoInventarioArticulo = new InventarioArticulo();
            nuevoInventarioArticulo.setArticulo(articulo);
            nuevoInventarioArticulo.setInventario(inventario);
            nuevoInventarioArticulo.setStockActual(cantidad);
            Optional<Demanda> demandaEspecifica = demandaRepository.findByArticuloAndPeriodoYear(articulo, 2023);
            if (demandaEspecifica.isEmpty()) {
                Demanda nuevaDemanda = new Demanda();
                nuevaDemanda.setArticulo(articulo);
                nuevaDemanda.setPeriodoYear(2023);
                demandaRepository.save(nuevaDemanda);
            }
            Random random = new Random();
            nuevoInventarioArticulo.setCostoAlmacenamiento(30 + random.nextInt(70));
            nuevoInventarioArticulo.setDesviacion(1 + random.nextInt(8));
            inventarioArticuloRepository.save(nuevoInventarioArticulo);
            inventarioArticuloRepository.save(nuevoInventarioArticulo);

        } else {

            for (InventarioArticulo inventarioArticulo : inventarioArticulos) {
                double nuevoStock = inventarioArticulo.getStockActual() + cantidad;
                inventarioArticulo.setStockActual(nuevoStock);
                inventarioArticuloRepository.save(inventarioArticulo);
            }
        }
    }

    @Override
    public void restarStock(Articulo articulo, int cantidad) {
        Articulo articuloExistente = articuloRepository.findById(articulo.getId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        LocalDate fechaActual = LocalDate.now();
        Inventario inventario = inventarioRepository.findByFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(fechaActual, fechaActual)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con esta fecha"));

        List<InventarioArticulo> inventarioArticulos = inventarioArticuloRepository.findByArticuloAndInventario(articuloExistente, inventario);
        if (inventarioArticulos == null) {
            throw new RuntimeException("InventarioArticulo no encontrado para el artículo especificado");
        }
        for (InventarioArticulo inventarioArticulo : inventarioArticulos) {
            if (inventarioArticulo.getStockActual() < cantidad) {
                throw new StockInsuficienteException("Stock insuficiente. Stock actual: "
                        + inventarioArticulo.getStockActual() + ", Cantidad requerida: " + cantidad);
            }

            inventarioArticulo.setStockActual(inventarioArticulo.getStockActual() - cantidad);
            inventarioArticuloRepository.save(inventarioArticulo);
        }
    }

    @Override
    public List<InventarioArticulo> getInventariosByArticulo(Long idArticulo) {
        Articulo articulo = articuloRepository.findById(idArticulo)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + idArticulo));
        return inventarioArticuloRepository.findAllByArticulo(articulo);
    }

    @Override
    public void calcularVariables(Long inventarioArticuloId) {

        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findById(inventarioArticuloId)
                .orElseThrow(() -> new RuntimeException("InventarioArticulo no encontrado"));

        Articulo articulo = inventarioArticulo.getArticulo();
        if (articulo == null) {
            throw new RuntimeException("Artículo asociado no encontrado en InventarioArticulo");
        }

        int Year = inventarioArticulo.getInventario().getFechaDesde().getYear()-1;

        // Obtén la demanda del artículo específico para el año actual
        Optional<Demanda> demandaEspecifica = demandaRepository.findByArticuloAndPeriodoYear(articulo, Year);
        if (demandaEspecifica.isEmpty()) {
            throw new RuntimeException("Demanda no encontrada para el artículo en el año actual");
        }

        ProveedorArticulo proveedorArticulo = proveedorService.getProveedorArticuloConMenorDemora(articulo.getId());  //buscar que sea del mismo articulo
        if (proveedorArticulo == null) {
            throw new RuntimeException("Proveedor no encontrado para el artículo");
        }

        double costoPedido = proveedorArticulo.getCostoPedido();
        int tiempoPedido = proveedorArticulo.getTiempoDemoraArticulo();
        double precioArt = articulo.getPrecioVenta();
        int costoalmacenamiento = inventarioArticulo.getCostoAlmacenamiento();
        int desviacion = inventarioArticulo.getDesviacion();

        String tipoModeloInventarioNombre = articulo.getArticuloCategoria().getTipoModeloInventario().getNombre();

        if ("Lote fijo".equals(tipoModeloInventarioNombre)) {

            double puntoPedido = tiempoPedido * (demandaEspecifica.get().getCantidad() / 300.0);
            double lotefijo = Math.sqrt(2.0 * demandaEspecifica.get().getCantidad() * (costoPedido / costoalmacenamiento));
            double stockSeguridad = 1.64 * desviacion * Math.sqrt(tiempoPedido);
            double cgi = precioArt * demandaEspecifica.get().getCantidad() + costoalmacenamiento * lotefijo / 2 + costoPedido * demandaEspecifica.get().getCantidad() / lotefijo;

            inventarioArticulo.setCGI(cgi);
            inventarioArticulo.setLoteFijo(lotefijo);
            inventarioArticulo.setPuntoPedido(puntoPedido);
            inventarioArticulo.setStockSeguridad(stockSeguridad);

        } else if ("Intervalo fijo".equals(tipoModeloInventarioNombre)) {
            double stockSeguridad = 1.64 * desviacion * Math.sqrt(tiempoPedido);
            double puntoPedido =stockSeguridad + tiempoPedido * (demandaEspecifica.get().getCantidad() / 300.0);
            inventarioArticulo.setLoteFijo(0);
            inventarioArticulo.setCGI(0);
            inventarioArticulo.setPuntoPedido(puntoPedido);
            inventarioArticulo.setStockSeguridad(stockSeguridad);

        } else {
            throw new IllegalArgumentException("Tipo de modelo de inventario no reconocido");
        }
        inventarioArticuloRepository.save(inventarioArticulo);
    }

    //Hecho por diego
    public double getLoteOptimoByArticulo(Long idArticulo) {
        Articulo articulo = articuloRepository.findById(idArticulo).orElseThrow(() -> new RuntimeException("Articulo no encontrado"));

        Inventario ultimoInventario = inventarioServices.obtenerUltimoInventario();
        List<InventarioArticulo> inventarioArticulos = inventarioArticuloRepository.findAll();

        final Double[] loteOptimo = {0.0};

        inventarioArticulos.forEach(inventarioArticulo -> {
            if (inventarioArticulo.getInventario().equals(ultimoInventario)) {
                if (inventarioArticulo.getArticulo().equals(articulo)) {
                    loteOptimo[0] = inventarioArticulo.getLoteFijo();
                }
            }
        });

        return loteOptimo[0];
    }

}
