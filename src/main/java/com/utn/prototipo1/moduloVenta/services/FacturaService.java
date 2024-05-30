package com.utn.prototipo1.moduloVenta.services;

import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.List;

public interface FacturaService {

    Factura createFactura(Factura factura);
    void addDetalleFactura(Long nroFactura, List<DetalleFactura> detalles);

}
