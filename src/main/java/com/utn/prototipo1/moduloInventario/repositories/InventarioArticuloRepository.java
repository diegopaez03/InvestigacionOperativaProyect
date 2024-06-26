package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioArticuloRepository extends BaseRepository<InventarioArticulo,Long> {
    List<InventarioArticulo> findByInventarioId(Long InventarioId);
    InventarioArticulo findByArticulo(Articulo articulo);
    @Transactional
    void deleteByInventarioId(Long inventarioId);

    //Creado por diego
    List<InventarioArticulo> findAllByArticulo(Articulo articulo);
    InventarioArticulo findByArticuloAndInventario(Articulo articulo, Inventario inventario);
}