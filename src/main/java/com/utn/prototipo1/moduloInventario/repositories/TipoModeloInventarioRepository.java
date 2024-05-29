package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloInventario.entities.TipoModeloInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoModeloInventarioRepository extends BaseRepository<TipoModeloInventario, Long> {
}
