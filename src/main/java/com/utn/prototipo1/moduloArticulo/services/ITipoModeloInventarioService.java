package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;

import java.util.List;

public interface ITipoModeloInventarioService {

    public List<TipoModeloInventario> getTipoModeloInventario();

    public TipoModeloInventario getTipoModeloInventarioById(Long id);

    public TipoModeloInventario saveTipoModeloInventario(TipoModeloInventario tipoModeloInventario);

    public void deleteTipoModeloInventario(TipoModeloInventario tipoModeloInventario);


}
