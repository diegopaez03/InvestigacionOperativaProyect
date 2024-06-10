package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;

import java.util.List;

public interface IArticuloCategoria {
    public List<ArticuloCategoria> getAllCategorias();

    public ArticuloCategoria saveCategoria(ArticuloCategoria articuloCategoria);

    public ArticuloCategoria getCategoriaById(Long id);

    public void deleteCategoria(Long id);
}
