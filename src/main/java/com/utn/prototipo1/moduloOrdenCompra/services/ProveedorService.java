package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.Proveedor;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

@Service
public class ProveedorService implements IProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorArticuloRepository proveedorArticuloRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

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

    @Override
    public List<Proveedor> getProveedoresConArticulo(Long idArticulo) {
    List<Proveedor> proveedores = new ArrayList<>();

    try {
        Articulo articulo = articuloRepository.findById(idArticulo)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el artículo con ID: " + idArticulo));

        List<ProveedorArticulo> proveedorArticulos = proveedorArticuloRepository.findProveedorArticuloByArticulo(articulo);

        proveedorArticulos.forEach(proveedorArticulo -> proveedores.add(getProveedorByProveedorArticulo(proveedorArticulo)));

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        // Aquí puedes considerar alguna acción adicional, como registrar la excepción en un archivo de log
    }

    return proveedores;
    }
    
    @Override
    public ProveedorArticulo getProveedorArticuloConMenorDemora(Long idArticulo) {
        List<Proveedor> proveedores = getProveedoresConArticulo(idArticulo);

        ProveedorArticulo proveedorConMenorTiempo = proveedores.stream()
            .map(proveedoresAll -> proveedoresAll.getProveedorArticulo())
            .flatMap(articulo -> articulo.stream())
            .min(Comparator.comparingInt(ProveedorArticulo::getTiempoDemoraArticulo))
            .orElseThrow(() -> new NoSuchElementException("No se encontró"));

        return proveedorConMenorTiempo;
    }

    public Proveedor getProveedorByProveedorArticulo(ProveedorArticulo proveedorArticulo) {
        return proveedorRepository.findProveedorByProveedorArticulo(proveedorArticulo);
    }

}
