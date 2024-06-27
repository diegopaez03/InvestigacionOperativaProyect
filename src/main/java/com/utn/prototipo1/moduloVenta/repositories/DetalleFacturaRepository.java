package com.utn.prototipo1.moduloVenta.repositories;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura,Long> {

    List<DetalleFactura> findByFacturaId(Long facturaId);

    DetalleFactura findByFacturaAndArticulo(Factura factura, Articulo articulo);
}
