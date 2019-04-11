package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.Department;
import com.labs.maven.springBoot.SpringBootMSC.Service.DepartmentService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService service;

    @Autowired
    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Department> getDepartment(@PathVariable Integer id){
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getAllDepartments(){
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Department dep){

        if (dep.getName() == null || dep.getFloor() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.saveObject(dep));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(service.saveObject(dep));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> update(@RequestBody Department newDep, @PathVariable Integer id){

        if (newDep.getName() == null || newDep.getFloor() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.updateObject(newDep, id));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(service.updateObject(newDep, id));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Map<String, Boolean> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        service.getById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        service.deleteObject(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
