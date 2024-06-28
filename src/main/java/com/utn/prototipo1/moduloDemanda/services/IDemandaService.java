package com.utn.prototipo1.moduloDemanda.services;

import java.util.List;

import com.utn.prototipo1.Base.services.BaseServices;
import com.utn.prototipo1.moduloDemanda.dtos.CrearDemandaDto;
import com.utn.prototipo1.moduloDemanda.entities.Demanda;

public interface IDemandaService  extends BaseServices<Demanda, Long>{

    public Demanda generarDemanda(CrearDemandaDto crearDemandaDto);

    public List<Demanda> getDemandasByArticulo(Long idArticulo);

    public List<Demanda> getDemandasByYear(int periodoYear);
}
