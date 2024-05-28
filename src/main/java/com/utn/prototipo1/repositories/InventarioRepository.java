package com.utn.prototipo1.repositories;

import com.utn.prototipo1.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {
}
