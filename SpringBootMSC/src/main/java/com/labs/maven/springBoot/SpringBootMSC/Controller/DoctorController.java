package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.Doctor;
import com.labs.maven.springBoot.SpringBootMSC.Service.DoctorService;
import javafx.application.Application;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@RefreshScope
public class DoctorController {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Value("${message_doctor:Doctor}")
    private String message;

    @RequestMapping("doctor/message")
    public String getDoctorMessage() {
        LOG.info("This is an info message(messageDoctor)");
        return message;
    }



    private DoctorService service;

    @Autowired
    public void setDoctorService(DoctorService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Doctor> getDoctor(@PathVariable Integer id){
        LOG.info("This is an info message(getOneDoctor)");
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Doctor> getAllDoctors(){
        LOG.info("This is an info message(getAllDoctors)");
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Doctor doc){

        LOG.info("This is an info message(createDoctor)");
        if (doc.getFname() == null || doc.getSurname() == null || doc.getAge() == null || doc.getSalary() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.saveObject(doc));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(service.saveObject(doc));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> update(@RequestBody Doctor newDoc, @PathVariable Integer id){

        LOG.info("This is an info message(updateDoctor)");
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
        LOG.info("This is an info message(deleteDoctor)");
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
