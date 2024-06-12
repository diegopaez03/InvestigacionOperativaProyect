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
    public List<Articulo> getAllArticulos() {
        return articuloRepository.findAll();
    }

    @Override
    public Articulo saveArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo getArticuloById(Long id) {
        return articuloRepository.findById(id).get();
    }

    @Override
    public void deleteArticulo(Long id) {
        articuloRepository.deleteById(id);
    }


}
