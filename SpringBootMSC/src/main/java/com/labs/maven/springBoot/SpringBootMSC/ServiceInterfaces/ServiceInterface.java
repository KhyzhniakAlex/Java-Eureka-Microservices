package com.labs.maven.springBoot.SpringBootMSC.ServiceInterfaces;

import com.labs.maven.springBoot.SpringBootMSC.Model.Doctor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceInterface<T> {

    Optional<T> getById(Integer id);
    List<T> getAll();
    T saveObject(T object);
    T updateObject(T newObject, Integer id);
    void deleteObject(Integer id);
}
