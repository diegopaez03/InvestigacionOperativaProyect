package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import java.util.List;

public interface ITipoModeloInventarioService {

    public List<TipoModeloInventario> getAllTiposModelosInventario();

    public TipoModeloInventario saveTipoModeloInventario(TipoModeloInventario tipoModeloInventario);

    public TipoModeloInventario getTipoModeloInventarioById(Long id);

    public void deleteTipoModeloInventario(Long id);
}

