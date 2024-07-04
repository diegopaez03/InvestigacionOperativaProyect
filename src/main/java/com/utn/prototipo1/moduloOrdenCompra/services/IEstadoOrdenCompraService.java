package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;

import java.util.List;

public interface IEstadoOrdenCompraService {

    public List<EstadoOrdenDeCompra> getEstadoOrdenCompra();

    public EstadoOrdenDeCompra getEstadoOrdenDeCompraById(Long id);

    public EstadoOrdenDeCompra saveEstadoOrdenDeCompra(EstadoOrdenDeCompra estadoOrdenDeCompra);

    public void deleteEstadoOrdenCompra(EstadoOrdenDeCompra estadoOrdenDeCompra);

    public EstadoOrdenDeCompra getEstadoOrdenDeCompraByNombre(String nombre) throws Exception;

}
