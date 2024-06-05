package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorArtículoRepository extends BaseRepository<ProveedorArticulo, Long> {
}
