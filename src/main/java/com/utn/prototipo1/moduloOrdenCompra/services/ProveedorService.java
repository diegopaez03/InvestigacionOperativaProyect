package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.dto.RegistrarProveedorDTO;
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
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

@Service
public class ProveedorService implements IProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorArticuloRepository proveedorArticuloRepository;

    @Autowired
    private ProveedorArticuloService proveedorArticuloService;

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

    public Proveedor registrarProveedor(RegistrarProveedorDTO registrarProveedorDTO) throws Exception {
        try {
            Proveedor proveedorRepetido = proveedorRepository.findProveedorByNombreProveedor(registrarProveedorDTO.getNombreProveedor());
            if (proveedorRepetido != null) {
                throw new Exception("El nombre del proveedor ya existe");
            }
    
            Proveedor proveedor = new Proveedor();

            proveedor.setNombreProveedor(registrarProveedorDTO.getNombreProveedor());
            
            List<ProveedorArticulo> proveedoresArticulos = new ArrayList<>();
            registrarProveedorDTO.getProveedorArticulos().forEach( detalle -> {
                if (detalle.getIdArticulo() != null) {
                    try {
                    ProveedorArticulo proveedorArticulo = proveedorArticuloService.generarProveedorArticulo(detalle.getIdArticulo(), detalle.getCostoPedido(), detalle.getTiempoDemoraArticulo());
    
                        proveedorArticuloService.save(proveedorArticulo);
                        proveedoresArticulos.add(proveedorArticulo);                    
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            proveedor.setProveedorArticulo(proveedoresArticulos);
    
            // Retornar el proveedor guardado
            return proveedorRepository.save(proveedor);
            
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
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
        // Obtiene los proveedores que tienen el artículo específico
        List<Proveedor> proveedores = getProveedoresConArticulo(idArticulo);

        // Filtra los ProveedorArticulo para que solo se consideren aquellos del artículo específico
        List<ProveedorArticulo> proveedorArticulosDelArticulo = proveedores.stream()
                .flatMap(proveedor -> proveedor.getProveedorArticulo().stream())
                .filter(proveedorArticulo -> proveedorArticulo.getArticulo().getId().equals(idArticulo))
                .collect(Collectors.toList());

        // Encuentra el ProveedorArticulo con el menor tiempo de demora
        ProveedorArticulo proveedorConMenorTiempo = proveedorArticulosDelArticulo.stream()
                .min(Comparator.comparingInt(ProveedorArticulo::getTiempoDemoraArticulo))
                .orElseThrow(() -> new NoSuchElementException("No se encontró proveedor con menor demora para el artículo con ID: " + idArticulo));

        return proveedorConMenorTiempo;
    }


    public Proveedor getProveedorByProveedorArticulo(ProveedorArticulo proveedorArticulo) {
        return proveedorRepository.findProveedorByProveedorArticulo(proveedorArticulo);
    }

    public List<Articulo> getArticulosOfProveedor(Proveedor proveedor) {
        List<ProveedorArticulo> proveedoresArticulo = proveedor.getProveedorArticulo();

        List<Articulo> articulos = new ArrayList<>();
        proveedoresArticulo.forEach(proveedorArticulo -> {
            articulos.add(proveedorArticulo.getArticulo());
        });

        return articulos;
    }

    @Override
    public Proveedor getProveedorByName(String nombre) {
        return proveedorRepository.findProveedorByNombreProveedor(nombre);
    }
}
