package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;

import java.util.List;

public interface IArticuloService {

    public List<Articulo> getArticulo();

    public Articulo getArticuloById(Long id);

    public Articulo saveArticulo(Articulo articulo);

    public void deleteArticulo(Articulo articulo);

}
