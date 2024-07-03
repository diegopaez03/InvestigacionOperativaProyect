package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.moduloOrdenCompra.entities.EstadoOrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.repositories.EstadoOrdenDeCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoOrdenCompraService implements IEstadoOrdenCompraService{

    @Autowired
    private EstadoOrdenDeCompraRepository estadoOrdenDeCompraRepository;

    @Override
    public List<EstadoOrdenDeCompra> getEstadoOrdenCompra() {
        return estadoOrdenDeCompraRepository.findAll();
    }

    @Override
    public EstadoOrdenDeCompra getEstadoOrdenDeCompraById(Long id) {
        EstadoOrdenDeCompra estadoOrdenDeCompra = estadoOrdenDeCompraRepository.findById(id).orElse(null);
        return estadoOrdenDeCompra;
    }

    @Override
    public EstadoOrdenDeCompra saveEstadoOrdenDeCompra(EstadoOrdenDeCompra estadoOrdenDeCompra) {
        return estadoOrdenDeCompraRepository.save(estadoOrdenDeCompra);
    }

    @Override
    public void deleteEstadoOrdenCompra(EstadoOrdenDeCompra estadoOrdenDeCompra) {
        estadoOrdenDeCompraRepository.delete(estadoOrdenDeCompra);
    }

    @Override
    public EstadoOrdenDeCompra getEstadoOrdenDeCompraByNombre(String nombre) throws Exception {
        EstadoOrdenDeCompra estadoOrdenDeCompra = estadoOrdenDeCompraRepository.findByNombreEOC(nombre);
        
        if (estadoOrdenDeCompra == null) {
            throw new Exception("No existe un estado con ese nombre");
        }

        return estadoOrdenDeCompra;
    }

}
