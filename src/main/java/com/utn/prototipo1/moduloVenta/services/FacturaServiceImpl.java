package com.utn.prototipo1.moduloVenta.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;
import com.utn.prototipo1.moduloVenta.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public Factura createFactura(Factura factura) {
        for (DetalleFactura detalle : factura.getDetalleFacturas()) {
            Articulo articulo = articuloRepository.findById(detalle.getArticulo().getId())
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
            detalle.setArticulo(articulo);
            detalle.setLinea(detalle.getCantidad() * articulo.getPrecio());
            detalle.setFactura(factura);
        }
        factura.calcularTotal();
        return facturaRepository.save(factura);
    }

    @Override
    public void addDetalleFactura(Long facturaId, List<DetalleFactura> detalles) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        for (DetalleFactura detalle : detalles) {
            Articulo articulo = articuloRepository.findById(detalle.getArticulo().getId())
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
            detalle.setArticulo(articulo);
            detalle.setLinea(detalle.getCantidad() * articulo.getPrecio());
            factura.addDetalleFactura(detalle);
        }
        facturaRepository.save(factura);
    }
}
