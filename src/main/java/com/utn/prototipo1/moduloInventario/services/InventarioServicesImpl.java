package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioServicesImpl extends BaseServicesImpl<Inventario, Long> implements InventarioServices{

    @Autowired

    private InventarioRepository inventarioRepository;

    public InventarioServicesImpl(BaseRepository<Inventario, Long> baseRepository){
        super(baseRepository);
    }
}
