package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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
    public InventarioArticulo deleteById(Long inventarioId) {
        inventarioArticuloRepository.deleteById(inventarioId);
        return null;
    }

    @Override
    public void actualizarStockPorFactura(Factura factura) {
        for (DetalleFactura detalle : factura.getDetalleFacturas()) {
            restarStock(detalle.getArticulo(), detalle.getCantidad());
        }
    }

    @Override
    public void sumarStock(Articulo articulo, float cantidad) {
        Articulo articuloExistente = articuloRepository.findById(articulo.getId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        LocalDate fechaActual = LocalDate.now();
        Inventario inventario = inventarioRepository.findByFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(fechaActual, fechaActual)
                .orElseGet(() -> {
                    Inventario nuevoInventario = new Inventario();
                    nuevoInventario.setFechaDesde(fechaActual);
                    nuevoInventario.setFechaHasta(fechaActual.plusDays(30));
                    inventarioRepository.save(nuevoInventario);
                    return nuevoInventario;
                });

        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticuloAndInventario(articuloExistente, inventario);
        if (inventarioArticulo == null) {
            inventarioArticulo = new InventarioArticulo();
            inventarioArticulo.setArticulo(articuloExistente);
            inventarioArticulo.setInventario(inventario);
            inventario.getInventarioArticulos().add(inventarioArticulo);
            inventarioArticulo.setStockActual(0);
        }
        inventarioArticulo.setStockActual(inventarioArticulo.getStockActual() + cantidad);
        inventarioArticuloRepository.save(inventarioArticulo);
    }

    @Override
    public void restarStock(Articulo articulo, int cantidad) {
        Articulo articuloExistente = articuloRepository.findById(articulo.getId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        LocalDate fechaActual = LocalDate.now();
        Inventario inventario = inventarioRepository.findByFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(fechaActual, fechaActual)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con esta fecha"));

        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticuloAndInventario(articuloExistente, inventario);
        if (inventarioArticulo == null) {
            throw new RuntimeException("InventarioArticulo no encontrado para el artículo especificado");
        }

        if (inventarioArticulo.getStockActual() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente. Stock actual: "
                    + inventarioArticulo.getStockActual() + ", Cantidad requerida: " + cantidad);
        }

        inventarioArticulo.setStockActual(inventarioArticulo.getStockActual() - cantidad);
        inventarioArticuloRepository.save(inventarioArticulo);
    }

    public List<InventarioArticulo> getInventariosByArticulo(Long idArticulo) {
        Articulo articulo = articuloRepository.findById(idArticulo)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + idArticulo));
        return inventarioArticuloRepository.findAllByArticulo(articulo);
    }

    @Override
    public void calcularVariables(Long inventarioArticuloId, Double costoAlmacenamiento, Double desviacion) {
        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findById(inventarioArticuloId)
                .orElseThrow(() -> new RuntimeException("InventarioArticulo no encontrado"));

        Articulo articulo = inventarioArticulo.getArticulo();
        if (articulo == null) {
            throw new RuntimeException("Artículo asociado no encontrado en InventarioArticulo");
        }

        Demanda demanda = demandaRepository.findByArticulo(articulo);
        if (demanda == null) {
            throw new RuntimeException("Demanda no encontrada para el artículo");
        }

        ProveedorArticulo proveedorArticulo = proveedorService.getProveedorArticuloConMenorDemora(articulo.getId());
        if (proveedorArticulo == null) {
            throw new RuntimeException("Proveedor no encontrado para el artículo");
        }

        double cantdemanda = demanda.getCantidad();
        double costoPedido = proveedorArticulo.getCostoPedido();
        double tiempoPedido = proveedorArticulo.getTiempoDemoraArticulo();
        double precioArt = articulo.getPrecioVenta();

        String tipoModeloInventarioNombre = articulo.getArticuloCategoria().getTipoModeloInventario().getNombre();

        if ("Lote fijo".equals(tipoModeloInventarioNombre)) {
            double puntoPedido = tiempoPedido * (cantdemanda/300.0);
            double lotefijo = Math.sqrt(2.0 * cantdemanda * (costoPedido / costoAlmacenamiento));
            double stockSeguridad = 1.64 * desviacion * Math.sqrt(tiempoPedido);
            double cgi = precioArt * cantdemanda + costoAlmacenamiento * lotefijo / 2 + costoPedido * cantdemanda / lotefijo;

            inventarioArticulo.setCGI(cgi);
            inventarioArticulo.setLoteFijo(lotefijo);
            inventarioArticulo.setPuntoPedido(puntoPedido);
            inventarioArticulo.setStockSeguridad(stockSeguridad);

        } else if ("Intervalo fijo".equals(tipoModeloInventarioNombre)) {
            double lotefijo = Math.sqrt(2.0 * cantdemanda * (costoPedido / costoAlmacenamiento));
            double stockSeguridad = 1.64 * desviacion * Math.sqrt(tiempoPedido);
            double cgi = precioArt * cantdemanda + costoAlmacenamiento * lotefijo / 2 + costoPedido * cantdemanda / lotefijo;
            inventarioArticulo.setLoteFijo(lotefijo);
            inventarioArticulo.setCGI(cgi);
            inventarioArticulo.setStockSeguridad(stockSeguridad);

        } else {
            throw new IllegalArgumentException("Tipo de modelo de inventario no reconocido");
        }

        inventarioArticuloRepository.save(inventarioArticulo);
    }
}
