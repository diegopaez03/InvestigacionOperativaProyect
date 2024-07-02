package com.utn.prototipo1.moduloDemanda.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DemandaRepository extends BaseRepository<Demanda, Long> {

    Demanda findByArticulo(Articulo articulo);

    List<Demanda> findAllByArticulo(Articulo articulo);

    List<Demanda> findAllByPeriodoYear(int periodoYear);

    Demanda findByArticuloAndPeriodoYear(Articulo articulo, int periodoYear);
}
