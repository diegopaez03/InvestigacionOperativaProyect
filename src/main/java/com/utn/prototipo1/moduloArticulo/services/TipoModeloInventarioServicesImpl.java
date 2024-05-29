package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.TipoModeloInventario;
import com.utn.prototipo1.moduloArticulo.repositories.TipoModeloInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoModeloInventarioServicesImpl extends BaseServicesImpl<TipoModeloInventario, Long> implements TipoModeloInventarioServices {
    @Autowired

    private TipoModeloInventarioRepository tipoModeloInventarioRepository;

    public TipoModeloInventarioServicesImpl(BaseRepository<TipoModeloInventario, Long> baseRepository){
        super(baseRepository);
    }
}
