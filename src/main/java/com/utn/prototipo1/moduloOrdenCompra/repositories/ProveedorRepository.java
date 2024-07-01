package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends BaseRepository<Proveedor,Long> {

    Optional<Proveedor> findById(Long id);
    public Proveedor findProveedorByProveedorArticulo(ProveedorArticulo proveedorArticulo);
    
}
