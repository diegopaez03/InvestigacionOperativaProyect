package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra,Long> {
}
