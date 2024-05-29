package com.utn.prototipo1.moduloInventario.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloInventario.entities.Inventario;
import com.utn.prototipo1.moduloInventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class InventarioServices extends BaseServicesImpl<Inventario, Long> {
    @Autowired
    private InventarioRepository inventarioRepository;
    public InventarioServices(BaseRepository<Inventario, Long> baseRepository){
        super(baseRepository);
    }
}
