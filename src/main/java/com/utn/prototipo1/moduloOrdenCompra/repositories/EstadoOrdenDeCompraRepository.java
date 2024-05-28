package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoOrdenDeCompraRepository extends JpaRepository<EstadoOrdenDeCompra,Long> {
}
