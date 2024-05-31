package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloInventario.entities.InventarioArticulo;
import com.utn.prototipo1.moduloInventario.repositories.InventarioArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioArticuloServiceImpl extends BaseServicesImpl<InventarioArticulo, Long> implements InventarioArticuloService{

    @Autowired
    private InventarioArticuloRepository inventarioArticuloRepository;

    public InventarioArticuloServiceImpl(BaseRepository<InventarioArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
