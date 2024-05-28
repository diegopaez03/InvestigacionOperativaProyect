package com.utn.prototipo1.moduloVenta.repositories;

import com.utn.prototipo1.moduloVenta.entities.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura,Long> {
}
