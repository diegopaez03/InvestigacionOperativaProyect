package com.utn.prototipo1.moduloVenta.services;

import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.List;

public interface FacturaService {

    List<Factura> obtenerTodasLasFacturas();

    void crearFactura(Factura factura);

    public Factura deleteFactura(Long id);

    Factura obtenerFacturaPorId(Long id);

    void actualizarTotalFactura(Long facturaId);

    public void actualizarStockPorDetalleFactura(DetalleFactura detalleFactura);

    public List<Factura> buscarFacturasFechaArticulo(CrearDemandaDto crearDemandaDto);

}
