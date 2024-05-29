package com.utn.prototipo1.moduloArticulo.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoModeloInventarioRepository extends BaseRepository<TipoModeloInventario, Long> {
}
