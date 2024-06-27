package com.utn.prototipo1.moduloVenta.repositories;

import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
    
    @Query("SELECT f FROM Factura f WHERE YEAR(f.fechaFactura) = :year")
    List<Factura> findFacturasByYear(@Param("year") int year);
}
