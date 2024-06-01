package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends BaseRepository<Inventario,Long> {
}
