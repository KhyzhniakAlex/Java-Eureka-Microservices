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
//@RequestMapping("/rest/department")
public class DepartmentController {

    private RestClient client;

    @Autowired
    public DepartmentController(RestClient client) {
        this.client = client;
    }

    @GetMapping("rest/department")
    public List<Department> getAllDepartments() {
        return client.getAllDepartments();
    }

    @GetMapping("rest/department/{id}")
    public Department getOneDepartment(@PathVariable Integer id) {
        return client.getOneDepartment(id);
    }

    @PostMapping("rest/department")
    public ResponseEntity<Object> createDepartment(@Valid @RequestBody Department department) {
        return client.createDepartment(department);
    }

    @PostMapping("rest/department/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable Integer id, @RequestBody Department newDepartment) {
        return client.updateDepartment(id, newDepartment);
    }

    @GetMapping("rest/department/delete/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable Integer id) {
        return client.deleteDepartment(id);
    }
}
