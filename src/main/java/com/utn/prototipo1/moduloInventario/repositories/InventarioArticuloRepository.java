package com.utn.prototipo1.moduloInventario.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioArticuloRepository extends BaseRepository<InventarioArticulo,Long> {
    List<InventarioArticulo> findByInventarioId(Long InventarioId);
    InventarioArticulo findByArticulo(Articulo articulo);
    @Transactional
    void deleteByInventarioId(Long inventarioId);

    @Query("SELECT ia FROM InventarioArticulo ia WHERE ia.stockActual <= ia.stockSeguridad")
    List<InventarioArticulo> findAllByStockActualLessThanOrEqualToStockSeguridad();
    @Query("SELECT ia FROM InventarioArticulo ia WHERE ia.stockActual <= ia.puntoPedido")
    List<InventarioArticulo> findAllByStockActualLessThanOrEqualToPuntoPedido();

    //Creado por diego
    List<InventarioArticulo> findAllByArticulo(Articulo articulo);
    List<InventarioArticulo> findByArticuloAndInventario(Articulo articulo, Inventario inventario);
}