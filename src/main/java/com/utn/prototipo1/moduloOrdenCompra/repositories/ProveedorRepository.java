package com.utn.prototipo1.moduloOrdenCompra.repositories;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
}
