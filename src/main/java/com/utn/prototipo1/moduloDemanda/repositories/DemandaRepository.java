package com.utn.prototipo1.moduloDemanda.repositories;

import com.utn.prototipo1.moduloDemanda.entities.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandaRepository extends JpaRepository<Demanda,Long> {

}
