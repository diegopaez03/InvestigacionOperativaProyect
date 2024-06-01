package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;

import java.util.List;

public interface IArticuloCategoria {
    public List<ArticuloCategoria> getArticuloCategoria();

    public ArticuloCategoria getArticuloCategoriaById(Long id);

    public ArticuloCategoria saveArticuloCategoria(ArticuloCategoria articuloCategoria);

    public void deleteArticuloCategoria(ArticuloCategoria articuloCategoria);
}
