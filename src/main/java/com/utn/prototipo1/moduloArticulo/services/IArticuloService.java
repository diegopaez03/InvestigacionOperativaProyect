package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;

import java.util.List;

public interface IArticuloService {

    public List<Articulo> getAllArticulos();

    public Articulo saveArticulo(Articulo articulo);

    public Articulo getArticuloById(Long id);

    public void deleteArticulo(Long id);


}
