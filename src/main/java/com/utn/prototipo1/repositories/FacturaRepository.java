package com.utn.prototipo1.repositories;

import com.utn.prototipo1.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
}
