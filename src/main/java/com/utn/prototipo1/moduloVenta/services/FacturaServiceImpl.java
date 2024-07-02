package com.utn.prototipo1.moduloVenta.services;


import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloInventario.services.InventarioArticuloService;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.DetalleFacturaRepository;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private  DetalleFacturaRepository detalleFacturaRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private InventarioArticuloService inventarioArticuloService;
    @Autowired
    DetalleFacturaService detalleFacturaService;

    @Override
    public Factura deleteFactura(Long id) {
        facturaRepository.deleteById(id);
        return null;
    }

    @Override
    public Factura obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public void crearFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    @Override
    public void actualizarStockPorDetalleFactura(DetalleFactura detalleFactura) {
        inventarioArticuloService.restarStock(detalleFactura.getArticulo(), detalleFactura.getCantidad());
    }

    @Override
    public void actualizarTotalFactura(Long facturaId) {
        Factura factura = obtenerFacturaPorId(facturaId);
        double total = 0.0; // Inicializa el total en 0.0

        // Itera sobre todos los detalles de factura asociados a la factura
        for (DetalleFactura detalle : detalleFacturaRepository.findByFacturaId(facturaId)) {
            total += detalle.getLinea(); // Suma el valor de línea de cada detalle
        }

        factura.setTotal(total); // Establece el total en la factura
        facturaRepository.save(factura);
    }


    public List<Factura> buscarFacturasFechaArticulo(CrearDemandaDto crearDemandaDto) {
        List<Factura> facturas = facturaRepository.findFacturasByYear(crearDemandaDto.getPeriodoYear());

        Articulo articulo = articuloRepository.findById(crearDemandaDto.getIdArticulo())
                .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + crearDemandaDto.getIdArticulo()));

        List<Factura> facturasConArticulo = new ArrayList<>();
        facturas.forEach(factura -> {
            // Obtener todos los detalles de la factura
            List<DetalleFactura> detallesFactura =  detalleFacturaService.obtenerDetallesPorFactura(factura.getId());
            // Filtrar los detalles que corresponden al artículo
            boolean contieneArticulo = detallesFactura.stream()
                    .anyMatch(detalle -> detalle.getArticulo().getId().equals(articulo.getId()));
            if (contieneArticulo) {
                facturasConArticulo.add(factura);
            }
        });

        return facturasConArticulo;
    }


}


