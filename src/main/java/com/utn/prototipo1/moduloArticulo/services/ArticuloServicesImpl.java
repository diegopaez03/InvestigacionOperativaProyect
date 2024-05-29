package com.utn.prototipo1.moduloArticulo.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloArticulo.entities.Articulo;
import com.utn.prototipo1.moduloArticulo.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServicesImpl extends BaseServicesImpl<Articulo,Long> implements ArticuloServices {
    @Autowired
    private ArticuloRepository articuloRepository;

    public ArticuloServicesImpl(BaseRepository<Articulo, Long> baseRepository){

        super(baseRepository);
    }
}
