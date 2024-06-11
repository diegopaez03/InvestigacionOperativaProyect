package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends BaseRepository<Proveedor,Long> {
}
