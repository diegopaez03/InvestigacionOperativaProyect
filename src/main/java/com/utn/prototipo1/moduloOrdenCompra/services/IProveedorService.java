package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;

import java.util.List;

public interface IProveedorService {

    public List<Proveedor> getProveedor();

    public Proveedor getProveedorById(Long id);

    public Proveedor saveProveedor(Proveedor proveedor);

    public void deleteProveedor(Proveedor proveedor);

    public List<Proveedor> getProveedoresConArticulo(Long idArticulo);

    public ProveedorArticulo getProveedorArticuloConMenorDemora(Long idArticulo);

}
