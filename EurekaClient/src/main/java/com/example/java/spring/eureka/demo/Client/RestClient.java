package com.example.java.spring.eureka.demo.Client;

import com.example.java.spring.eureka.demo.Model.Department;
import com.example.java.spring.eureka.demo.Model.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("lab")
@RestController
public interface RestClient {

    @GetMapping("/doctor/")
    List<Doctor> getAllDoctors();

    @PostMapping("/doctor/")
    ResponseEntity<Object> createDoctor(@RequestBody Doctor doctor);

    @GetMapping("/doctor/{id}")
    Doctor getOneDoctor(@PathVariable Integer id);

    @PostMapping("/doctor/{id}")
    ResponseEntity<Object> updateDoctor(@PathVariable Integer id, @RequestBody Doctor newDoctor);

    @GetMapping("/doctor/delete/{id}")
    Map<String, Boolean> deleteDoctor(@PathVariable Integer id);



    @GetMapping("/department/")
    List<Department> getAllDepartments();

    @PostMapping("/department/")
    ResponseEntity<Object> createDepartment(@RequestBody Department department);

    @GetMapping("/department/{id}")
    Department getOneDepartment(@PathVariable Integer id);

    @PostMapping("/department/{id}")
    ResponseEntity<Object> updateDepartment(@PathVariable Integer id, @RequestBody Department newDepartment);

    @GetMapping("/department/delete/{id}")
    Map<String, Boolean> deleteDepartment(@PathVariable Integer id);
}
