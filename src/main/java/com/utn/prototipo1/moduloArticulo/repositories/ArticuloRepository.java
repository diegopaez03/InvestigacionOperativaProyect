package com.utn.prototipo1.moduloArticulo.repositories;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
    List<Articulo>findByArticuloCategoria(ArticuloCategoria articuloCategoria);
}
