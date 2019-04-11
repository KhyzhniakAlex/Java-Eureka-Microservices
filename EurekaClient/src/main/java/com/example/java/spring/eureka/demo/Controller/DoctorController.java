package com.example.java.spring.eureka.demo.Controller;

import com.example.java.spring.eureka.demo.Client.RestClient;
import com.example.java.spring.eureka.demo.Model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/doctor")
public class DoctorController {

    private RestClient client;

    @Autowired
    public void setDoctorController(RestClient client) {
        this.client = client;
    }

    //@GetMapping("rest/doctor-list")
    @RequestMapping(method = RequestMethod.GET)
    public List<Doctor> getAllDoctors() {
        return client.getAllDoctors();
    }

    //@GetMapping("rest/doctor{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Doctor getOneDoctor(@PathVariable Integer id) {
        return client.getOneDoctor(id);
    }

    //@PostMapping("rest/doctor-create")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createDoctor(@Valid @RequestBody Doctor doctor) {
        return client.createDoctor(doctor);
    }

    //@PutMapping("rest/doctor{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateDoctor(@PathVariable Integer id, @RequestBody Doctor newDoctor) {
        return client.updateDoctor(id, newDoctor);
    }

    //@DeleteMapping("rest/doctor{id}")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Map<String, Boolean> deleteDoctor(@PathVariable Integer id) {
        return client.deleteDoctor(id);
    }
}
