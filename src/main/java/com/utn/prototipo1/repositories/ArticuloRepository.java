package com.utn.prototipo1.repositories;

import com.utn.prototipo1.entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
}
