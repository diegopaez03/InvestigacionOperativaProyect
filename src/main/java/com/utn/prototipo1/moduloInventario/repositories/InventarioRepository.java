package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface InventarioRepository extends BaseRepository<Inventario,Long> {
    Optional<Inventario> findByFechaDesdeLessThanEqualAndFechaHastaGreaterThanEqual(LocalDate fechaDesde, LocalDate fechaHasta);
}
