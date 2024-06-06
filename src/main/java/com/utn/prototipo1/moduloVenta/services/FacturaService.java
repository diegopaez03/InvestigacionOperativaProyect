package com.utn.prototipo1.moduloVenta.services;

import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.List;

public interface FacturaService {

    List<Factura> obtenerTodasLasFacturas();
    void crearFactura(Factura factura);
    public Factura deleteFactura(Long id);
    Factura obtenerFacturaPorId(Long id);
   // public DetalleFactura deleteDetalleFactura(Long id);

}
