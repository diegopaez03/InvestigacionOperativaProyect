package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServicesImpl implements InventarioServices{

    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private  InventarioArticuloRepository inventarioArticuloRepository;

    @Override
    public Inventario deleteInventario(Long id) {
        inventarioRepository.deleteById(id);
        return null;
    }

    @Override
    public Inventario obtenerInventarioId(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Inventario> obtenerTodosLosInventarios() {
        return inventarioRepository.findAll();
    }

    @Override
    public void crearInventario(Inventario inventario) {
        inventarioRepository.save(inventario);
    }

    }