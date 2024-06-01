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
    public List<ArticuloCategoria> getArticuloCategoria() {
        return articuloCategoriaRepository.findAll();
    }

    @Override
    public ArticuloCategoria getArticuloCategoriaById(Long id) {
        ArticuloCategoria articuloCategoria = articuloCategoriaRepository.findById(id).orElse(null);
        return articuloCategoria;
    }

    @Override
    public ArticuloCategoria saveArticuloCategoria(ArticuloCategoria articuloCategoria) {
        return articuloCategoriaRepository.save(articuloCategoria);
    }

    @Override
    public void deleteArticuloCategoria(ArticuloCategoria articuloCategoria) {
        articuloCategoriaRepository.delete(articuloCategoria);
    }
}
