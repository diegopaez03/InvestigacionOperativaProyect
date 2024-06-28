package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.services.BaseServices;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;

import java.util.List;

public interface InventarioServices  {

    List<Inventario> findAll();
    void crearInventario(Inventario inventario);
    public Inventario deleteById(Long id);
    Inventario obtenerInventarioPorId(Long id);

    public Inventario obtenerUltimoInventario();
}