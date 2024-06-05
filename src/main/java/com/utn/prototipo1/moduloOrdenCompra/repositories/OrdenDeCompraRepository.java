package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeCompraRepository extends BaseRepository<OrdenDeCompra,Long> {
}
