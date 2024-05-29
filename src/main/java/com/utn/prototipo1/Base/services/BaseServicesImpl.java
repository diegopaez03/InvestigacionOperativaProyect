package com.utn.prototipo1.Base.services;

import com.utn.prototipo1.Base.entities.BaseEntidad;
import com.utn.prototipo1.Base.repositories.BaseRepository;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServicesImpl<E extends BaseEntidad, ID extends Serializable> implements BaseServices<E, ID> {

    protected BaseRepository<E, ID> baseRepository;
    public BaseServicesImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository=baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            List<E> entities = baseRepository.findAll();
            return entities;
        }   catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entitieOptional = baseRepository.findById(id);
            return entitieOptional.get();
    }   catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }
    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if (baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
