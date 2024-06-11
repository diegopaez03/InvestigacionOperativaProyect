package com.utn.prototipo1.moduloOrdenCompra.services;

import java.util.List;

import com.utn.prototipo1.Base.services.BaseServices;
import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;

public interface IOrdenDeCompraService extends BaseServices<OrdenDeCompra, Long> {

    public List<OrdenDeCompra> getOrdenesDeCompra();
}
