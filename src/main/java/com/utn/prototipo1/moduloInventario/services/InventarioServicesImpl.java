package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventarioServicesImpl implements InventarioServices{

    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private  InventarioArticuloRepository inventarioArticuloRepository;

    @Override
    @Transactional
    public Inventario deleteById(Long id) {
        inventarioArticuloRepository.deleteByInventarioId(id);
        inventarioRepository.deleteById(id);
        return null;
    }
    @Override
    public Inventario obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    @Override
    public void crearInventario(Inventario inventario) {
        inventario.setFechaDesde(LocalDate.now());

        // Guardar el inventario para obtener su ID asignado
        inventarioRepository.save(inventario);

        // Iterar sobre los InventarioArticulo asociados al nuevo inventario
        for (InventarioArticulo inventarioArticulo : inventario.getInventarioArticulos()) {
            // Establecer la relación bidireccional
            inventarioArticulo.setInventario(inventario);
            // Guardar cada InventarioArticulo
            inventarioArticuloRepository.save(inventarioArticulo);
        }
    }

    public Inventario obtenerUltimoInventario() {
        List<Inventario> inventarios = inventarioRepository.findAllByOrderByFechaHastaDesc();
        if (!inventarios.isEmpty()) {
            return inventarios.get(0);
        } else {
            return null;
        }
    }
}