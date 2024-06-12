package com.utn.prototipo1.moduloInventario.services;


import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioArticuloServiceImpl  implements InventarioArticuloService{

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;

    @Override
    public List<InventarioArticulo> findAll() {
        return inventarioArticuloRepository.findAll();
    }

    @Override
    public InventarioArticulo findById(Long id) {
        return inventarioArticuloRepository.findById(id).orElse(null);
    }

    @Override
    public InventarioArticulo save(InventarioArticulo inventarioArticulo) {
        return inventarioArticuloRepository.save(inventarioArticulo);

    }

    @Override
        public List<InventarioArticulo> obtenerInventarioArticulos(Long InventarioId) {
        return inventarioArticuloRepository.findByInventarioId(InventarioId);
    }

    @Override
    public InventarioArticulo deleteById(Long InventarioId) {
        inventarioArticuloRepository.deleteById(InventarioId);
        return null;
    }
}