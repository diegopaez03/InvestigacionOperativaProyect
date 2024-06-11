package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.TipoModeloInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoModeloInventarioService implements ITipoModeloInventarioService {

    @Autowired
    private TipoModeloInventarioRepository tipoModeloInventarioRepository;

    @Override
    public List<TipoModeloInventario> getAllTiposModelosInventario() {
        return tipoModeloInventarioRepository.findAll();
    }

    @Override
    public TipoModeloInventario saveTipoModeloInventario(TipoModeloInventario tipoModeloInventario) {
        return tipoModeloInventarioRepository.save(tipoModeloInventario);
    }

    @Override
    public TipoModeloInventario getTipoModeloInventarioById(Long id) {
        return tipoModeloInventarioRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTipoModeloInventario(Long id) {
        tipoModeloInventarioRepository.deleteById(id);
    }
}

