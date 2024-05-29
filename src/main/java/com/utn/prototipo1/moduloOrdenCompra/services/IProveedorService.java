package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;

import java.util.List;

public interface IProveedorService {

    public List<Proveedor> getProveedor();

    public Proveedor getProveedorById(Long id);

    public Proveedor saveProveedor(Proveedor proveedor);

    public void deleteProveedor(Proveedor proveedor);

}
