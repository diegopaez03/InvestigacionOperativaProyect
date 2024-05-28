package com.utn.prototipo1.moduloVenta.repositories;

import com.utn.prototipo1.moduloVenta.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
}
