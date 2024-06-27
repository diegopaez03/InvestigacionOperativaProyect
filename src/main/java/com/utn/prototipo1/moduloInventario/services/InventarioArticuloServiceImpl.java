package com.utn.prototipo1.moduloInventario.services;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloArticulo.services.ArticuloService;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;
import com.utn.prototipo1.moduloDemanda.services.DemandaService;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;

import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import com.utn.prototipo1.moduloOrdenCompra.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventarioArticuloServiceImpl  implements InventarioArticuloService{

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
    private ArticuloService articuloService;


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
        public List<InventarioArticulo> obtenerArticuloPorInventario(Long InventarioId) {
        return inventarioArticuloRepository.findByInventarioId(InventarioId);
    }



    @Override
    public InventarioArticulo deleteById(Long InventarioId) {
        inventarioArticuloRepository.deleteById(InventarioId);
        return null;
    }

    @Override
    public void sumarStock(Articulo articulo, int cantidad) {

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

            InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticulo(articuloExistente);
            if (inventarioArticulo == null) {

                inventarioArticulo = new InventarioArticulo();
                inventarioArticulo.setArticulo(articuloExistente);
                Inventario inventario1 = inventarioRepository.findById(1L).
                        orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
                inventarioArticulo.setInventario(inventario1);
                inventarioArticulo.setStockActual(0);
            }
        inventarioArticulo.setStockActual(inventarioArticulo.getStockActual() + cantidad);
        inventarioArticuloRepository.save(inventarioArticulo);
    }
    @Override
    public void restarStock(Articulo articulo, int cantidad){
        Articulo articuloExistente = articuloRepository.findById(articulo.getId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        LocalDate fechaActual = LocalDate.now();
        Inventario inventario = inventarioRepository.findByFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(fechaActual, fechaActual)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con esta fecha"));


        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.findByArticulo(articuloExistente);
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

    @Override
    public void calcularVariables(Long inventarioArticuloId, String tipoModeloInventario, Double costoAlmacenamiento, Double desviacion) {
        InventarioArticulo inventarioArticulo = inventarioArticuloRepository.getReferenceById(inventarioArticuloId);
        double cantdemanda = demandaRepository.findByArticulo(inventarioArticulo.getArticulo()).getCantidad();
        double costoPedido = proveedorService.getProveedorArticuloConMenorDemora(inventarioArticulo.getArticulo().getId()).getCostoPedido();
        double tiempoPedido = proveedorService.getProveedorArticuloConMenorDemora(inventarioArticulo.getArticulo().getId()).getTiempoDemoraArticulo();
        double precioArt = articuloService.getArticuloById(inventarioArticulo.getArticulo().getId()).getPrecioVenta();

        if ("Lote fijo".equals(tipoModeloInventario)) {

            double puntoPedido = tiempoPedido * cantdemanda;
            double lotefijo = Math.sqrt(2*cantdemanda*(costoPedido/costoAlmacenamiento));
            double stockSeguridad =1.64 * desviacion*Math.sqrt(tiempoPedido);
            double cgi = precioArt*cantdemanda + costoAlmacenamiento*lotefijo/2 + costoPedido * cantdemanda/lotefijo;
            inventarioArticulo.setCGI(cgi);
            inventarioArticulo.setLoteFijo(lotefijo);
            inventarioArticulo.setPuntoPedido(puntoPedido);
            inventarioArticulo.setStockSeguridad(stockSeguridad);


        } else if ("Intervalo fijo".equals(tipoModeloInventario)) {

            double lotefijo = Math.sqrt(2*cantdemanda*(costoPedido/costoAlmacenamiento));
            double stockSeguridad =1.64 * desviacion*Math.sqrt(tiempoPedido);
            double cgi = precioArt*cantdemanda + costoAlmacenamiento*lotefijo/2 + costoPedido * cantdemanda/lotefijo;
            inventarioArticulo.setCGI(cgi);
            inventarioArticulo.setStockSeguridad(stockSeguridad);

        } else {
            throw new IllegalArgumentException("Tipo de modelo de inventario no reconocido");
        }

        inventarioArticuloRepository.save(inventarioArticulo);
    }
}