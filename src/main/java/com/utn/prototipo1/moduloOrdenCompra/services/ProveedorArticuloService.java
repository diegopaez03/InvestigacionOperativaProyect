package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloOrdenCompra.entities.ProveedorArticulo;
import com.utn.prototipo1.moduloOrdenCompra.repositories.ProveedorArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorArticuloService extends BaseServicesImpl<ProveedorArticulo, Long> implements IProveedorArticuloService{

    @Autowired
    private ProveedorArticuloRepository proveedorArtículoRepository;

    public ProveedorArticuloService(BaseRepository<ProveedorArticulo, Long> baseRepository) {
        super(baseRepository);
    }

}
