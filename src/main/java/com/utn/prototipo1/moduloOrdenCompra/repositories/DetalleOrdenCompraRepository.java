package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.DetalleOrdenCompra;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenCompraRepository extends BaseRepository<DetalleOrdenCompra, Long> {
}
