package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloService implements IArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public List<Articulo> getArticulo() {
        return articuloRepository.findAll();
    }

    @Override
    public Articulo getArticuloById(Long id) {
        Articulo articulo = articuloRepository.findById(id).orElse(null);
        return articulo;
    }

    @Override
    public Articulo saveArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public void deleteArticulo(Articulo articulo) {
        articuloRepository.delete(articulo);
    }
}
