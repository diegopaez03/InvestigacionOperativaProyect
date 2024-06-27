package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorArticuloRepository extends BaseRepository<ProveedorArticulo, Long> {

    public List<ProveedorArticulo> findProveedorArticuloByArticulo(Articulo articulo);
}
