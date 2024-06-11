package com.utn.prototipo1.moduloVenta.services;

import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.List;

public interface DetalleFacturaService {

    List<DetalleFactura> findAll();
    DetalleFactura findById(Long id);
    DetalleFactura save(DetalleFactura detalleFactura);
    List<DetalleFactura> obtenerDetallesPorFactura(Long facturaId);
    public DetalleFactura deleteById(Long detalleId);
}
