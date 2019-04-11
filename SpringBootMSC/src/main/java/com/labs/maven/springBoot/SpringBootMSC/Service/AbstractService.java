package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Department;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ThereIsNoSuchItemException;
import com.labs.maven.springBoot.SpringBootMSC.ServiceInterfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService {

    //<TEntity, TRepository extends CrudRepository<TEntity, Integer>> implements ServiceInterface<TEntity>

    /*@Autowired
    private TRepository repository;

    @Override
    public TEntity getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ThereIsNoSuchItemException());
    }

    @Override
    public List<TEntity> getAll() {
        //return repository.findAll();
        return null;
    }

    @Override
    public TEntity saveObject(TEntity object) {
        return null;
    }

    @Override
    public TEntity updateObject(TEntity newObject, Integer id) {
        return null;
    }

    @Override
    public void deleteObject(Integer id) {

    }*/
}
