package com.utn.prototipo1.moduloDemanda.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloVenta.entities.Factura;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandaRepository extends BaseRepository<Demanda, Long> {

    @Query("SELECT f FROM Factura f JOIN f.detalleFacturas df WHERE df.articulo.id = :idArticulo AND f.fechaFactura BETWEEN :fechaDesde AND :fechaHasta")
    List<Factura> findFacturasByFechaAndArticulo(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta, @Param("idArticulo") Long idArticulo);
}
