package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.moduloInventario.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {
}
