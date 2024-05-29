package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService implements IProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> getProveedor() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(Proveedor proveedor) {
        proveedorRepository.delete(proveedor);
    }
}
