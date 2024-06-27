package com.utn.prototipo1.moduloDemanda.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.utn.prototipo1.Base.repositories.BaseRepository;
import com.utn.prototipo1.Base.services.BaseServicesImpl;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import com.utn.prototipo1.moduloDemanda.repositories.DemandaRepository;

public class DemandaService extends BaseServicesImpl<Demanda, Long> implements IDemandaService {

    @Autowired
    private DemandaRepository demandaRepository;

    public DemandaService(BaseRepository<Demanda, Long> baseRepository) {
        super(baseRepository);
    }
}
