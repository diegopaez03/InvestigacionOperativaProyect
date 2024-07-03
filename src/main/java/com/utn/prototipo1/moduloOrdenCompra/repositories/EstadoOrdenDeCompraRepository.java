package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoOrdenDeCompraRepository extends BaseRepository<EstadoOrdenDeCompra,Long> {
    public EstadoOrdenDeCompra findByNombreEOC(String nombreEOC);
}
