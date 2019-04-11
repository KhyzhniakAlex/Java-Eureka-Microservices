package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.Doctor;
import com.labs.maven.springBoot.SpringBootMSC.Service.DoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService service;

    @Autowired
    public void setDoctorService(DoctorService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Doctor> getDoctor(@PathVariable Integer id){
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Doctor> getAllDoctors(){
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Doctor doc){

        if (doc.getFname() == null || doc.getSurname() == null || doc.getAge() == null || doc.getSalary() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.saveObject(doc));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(service.saveObject(doc));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> update(@RequestBody Doctor newDoc, @PathVariable Integer id){

        if (newDoc.getFname() == null || newDoc.getSurname() == null || newDoc.getAge() == null || newDoc.getSalary() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.updateObject(newDoc, id));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(service.updateObject(newDoc, id));
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
