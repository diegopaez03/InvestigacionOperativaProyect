package com.utn.prototipo1.moduloVenta.repositories;

import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura,Long> {

    List<DetalleFactura> findByFacturaId(Long facturaId);
}
