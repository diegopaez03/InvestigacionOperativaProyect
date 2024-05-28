package com.utn.prototipo1.moduloArticulo.repositories;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
}
