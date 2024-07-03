package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorArticuloService extends BaseServicesImpl<ProveedorArticulo, Long> implements IProveedorArticuloService{

    @Autowired
    private ArticuloRepository articuloRepository;

    public ProveedorArticuloService(BaseRepository<ProveedorArticulo, Long> baseRepository) {
        super(baseRepository);
    }

    public ProveedorArticulo generarProveedorArticulo(Long idArticulo, double costoPedido, int tiempoDemora) throws Exception {
        Articulo articulo = articuloRepository.findById(idArticulo).orElseThrow(() -> new EntityNotFoundException("Articulo not found"));

        ProveedorArticulo proveedorArticulo = new ProveedorArticulo();

        proveedorArticulo.setArticulo(articulo);
        proveedorArticulo.setCostoPedido(costoPedido);
        proveedorArticulo.setTiempoDemoraArticulo(tiempoDemora);

        return this.save(proveedorArticulo);
    }
}
