package com.utn.prototipo1.moduloOrdenCompra.services;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloOrdenCompra.entities.DetalleOrdenCompra;
import com.utn.prototipo1.moduloOrdenCompra.repositories.DetalleOrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenCompraService extends BaseServicesImpl<DetalleOrdenCompra, Long> implements IDetalleOrdenCompraService {

    @Autowired
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    public DetalleOrdenCompraService(BaseRepository<DetalleOrdenCompra, Long> baseRepository) {
        super(baseRepository);
    }
}
