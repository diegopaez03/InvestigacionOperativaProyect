package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloOrdenCompra.entities.OrdenDeCompra;
import com.utn.prototipo1.moduloOrdenCompra.repositories.OrdenDeCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenDeCompraService extends BaseServicesImpl<OrdenDeCompra, Long> implements IOrdenDeCompraService {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    public OrdenDeCompraService(BaseRepository<OrdenDeCompra, Long> baseRepository) {
        super(baseRepository);
    }

}
