package com.example.java.spring.eureka.demo.Controller;

import com.example.java.spring.eureka.demo.Client.RestClient;
import com.example.java.spring.eureka.demo.Model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/department")
public class DepartmentController {

    @Autowired
    private RestClient client;


    /*public void setDepartmentController(RestClient client) {
        this.client = client;
    }*/

    //@GetMapping("rest/")
    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getAllDepartments() {
        return client.getAllDepartments();
    }

    //@GetMapping("rest/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getOneDepartment(@PathVariable Integer id) {
        return client.getOneDepartment(id);
    }

    //@PostMapping("rest/")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createDepartment(@Valid @RequestBody Department department) {
        return client.createDepartment(department);
    }

    //@PostMapping("rest/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateDepartment(@PathVariable Integer id, @RequestBody Department newDepartment) {
        return client.updateDepartment(id, newDepartment);
    }

    //@GetMapping("rest/delete{id}")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Map<String, Boolean> deleteDepartment(@PathVariable Integer id) {
        return client.deleteDepartment(id);
    }
}
