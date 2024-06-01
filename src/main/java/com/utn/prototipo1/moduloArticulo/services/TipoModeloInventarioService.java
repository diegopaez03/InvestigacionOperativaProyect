package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.TipoModeloInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoModeloInventarioService implements ITipoModeloInventarioService{

    @Autowired
    private TipoModeloInventarioRepository tipoModeloInventarioRepository;

    @Override
    public List<TipoModeloInventario> getTipoModeloInventario() {
        return tipoModeloInventarioRepository.findAll();
    }

    @Override
    public TipoModeloInventario getTipoModeloInventarioById(Long id) {
        TipoModeloInventario tipoModeloInventario = tipoModeloInventarioRepository.findById(id).orElse(null);
        return tipoModeloInventario;
    }

    @Override
    public TipoModeloInventario saveTipoModeloInventario(TipoModeloInventario tipoModeloInventario) {
        return tipoModeloInventarioRepository.save(tipoModeloInventario);
    }

    @Override
    public void deleteTipoModeloInventario(TipoModeloInventario tipoModeloInventario) {
        tipoModeloInventarioRepository.delete(tipoModeloInventario);
    }

}
