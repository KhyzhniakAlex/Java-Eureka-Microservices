package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.Department;
import com.labs.maven.springBoot.SpringBootMSC.Service.DepartmentService;
import javafx.application.Application;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Value("${message_department:Department}")
    private String message;

    @RequestMapping("department/message")
    public String getDepartmentMessage() {
        LOG.info("This is an info message(messageDepartment)");
        return message;
    }



    private DepartmentService service;

    @Autowired
    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Department> getDepartment(@PathVariable Integer id){
        LOG.info("This is an info message(getOneDepartment)");
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getAllDepartments(){
        LOG.info("This is an info message(getAllDepartments)");
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Department dep){

        LOG.info("This is an info message(createDepartment)");
        if (dep.getName() == null || dep.getFloor() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.saveObject(dep));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(service.saveObject(dep));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> update(@RequestBody Department newDep, @PathVariable Integer id){

        LOG.info("This is an info message(updateDepartment)");
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
        LOG.info("This is an info message(deleteDepartment)");
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
