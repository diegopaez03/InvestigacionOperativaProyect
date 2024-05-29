package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioArticuloRepository extends JpaRepository<InventarioArticulo,Long> {
}