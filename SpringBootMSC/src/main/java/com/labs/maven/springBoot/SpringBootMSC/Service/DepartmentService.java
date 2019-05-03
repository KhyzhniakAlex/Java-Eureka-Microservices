package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Department;
import com.labs.maven.springBoot.SpringBootMSC.Model.Doctor;
import com.labs.maven.springBoot.SpringBootMSC.Model.ExceptionMessage;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.DepartmentRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ThereIsNoSuchItemException;
import com.labs.maven.springBoot.SpringBootMSC.ServiceInterfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements ServiceInterface<Department> {

    private DepartmentRepository repository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Department> getById(Integer id) {
        Optional<Department> department = repository.findById(id);
        if (!department.isPresent()) {
            ExceptionMessage em = new ExceptionMessage();
            em.setGap(null);
            throw new ThereIsNoSuchItemException();
        }
        else {
            department.map(dep -> {
                if (!dep.getPresenceFlag()) {
                    ExceptionMessage em = new ExceptionMessage();
                    em.setGap(null);
                    throw new ThereIsNoSuchItemException();
                }
                return dep;
            });
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = (List<Department>)repository.findAll();
        for(Department dep : departments) {
            if (!dep.getPresenceFlag()) {
                //for (Doctor doc : dep.getDoctors())
                //    dep.getDoctors().remove(doc);
                departments.remove(dep);
            }
        }
        return departments;
    }

    @Override
    public Department saveObject(Department department) {

        ExceptionMessage em = new ExceptionMessage();

        if (department.getName() == null) {
            em.setGap("Incorrect NAME");
            throw new InvalidInfoException();
        }
        else if (department.getFloor() == null){
            em.setGap("Incorrect FLOOR");
            throw new InvalidInfoException();
        }
        else{
            return repository.save(department);
        }
    }

    @Override
    public Department updateObject(Department newDep, Integer id) {

        ExceptionMessage em = new ExceptionMessage();

        if (newDep.getName() == null) {
            em.setGap("Incorrect NAME");
            throw new InvalidInfoException();
        }
        else if (newDep.getFloor() == null){
            em.setGap("Incorrect FLOOR");
            throw new InvalidInfoException();
        }
        else {
            return repository.findById(id)
                    .map(dep -> {
                        if (dep.getPresenceFlag()) {
                            dep.setName(newDep.getName());
                            dep.setFloor(newDep.getFloor());
                            return repository.save(dep);
                        }
                        else {
                            newDep.setId(id);
                            return repository.save(newDep);
                        }
                    })
                    .orElseGet(() -> {
                        newDep.setId(id);
                        return repository.save(newDep);
                    });
        }
    }

    /*@Override
    public void deleteDepartment(Integer id) {
        Optional<Department> dep = repository.findById(id);
        if (!dep.isPresent()) {
            ExceptionMessage em = new ExceptionMessage();
            em.setGap(null);
            throw new ThereIsNoSuchItemException();
        }

        repository.deleteById(id);
    }*/

    @Override
    public void deleteObject(Integer id) {
        if (!repository.findById(id).isPresent()) {
            ExceptionMessage em = new ExceptionMessage();
            em.setGap(null);
            throw new ThereIsNoSuchItemException();
        } else {
            repository.findById(id).map(department -> {
                if (!department.getPresenceFlag()) {
                    ExceptionMessage em = new ExceptionMessage();
                    em.setGap(null);
                    throw new ThereIsNoSuchItemException();
                } else {
                    department.setPresenceFlag(false);
                    return repository.save(department);
                }
            });
        }
    }
}
