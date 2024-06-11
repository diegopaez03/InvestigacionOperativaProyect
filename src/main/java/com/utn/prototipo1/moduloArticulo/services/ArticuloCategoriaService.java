package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.entities.ArticuloCategoria;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloCategoriaRepository;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloCategoriaService implements IArticuloCategoria {
    @Autowired
    private ArticuloCategoriaRepository articuloCategoriaRepository;

    @Override
    public List<ArticuloCategoria> getAllCategorias() {
        return articuloCategoriaRepository.findAll();
    }

    @Override
    public ArticuloCategoria saveCategoria(ArticuloCategoria articuloCategoria) {
        return articuloCategoriaRepository.save(articuloCategoria);
    }

    @Override
    public ArticuloCategoria getCategoriaById(Long id) {
        return articuloCategoriaRepository.findById(id).get();
    }

    @Override
    public void deleteCategoria(Long id) {
        articuloCategoriaRepository.deleteById(id);
    }
}
