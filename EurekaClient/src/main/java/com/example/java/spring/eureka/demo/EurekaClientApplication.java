package com.example.java.spring.eureka.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }



    private static Logger log = LoggerFactory.getLogger(EurekaClientApplication.class);

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient client;

    @RequestMapping(value = "/instances")
    public String getInstancesRun(){
        ServiceInstance instance = client.choose("a-bootiful-client");
        return instance.getUri().toString();
    }

    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.GET)
    public String getDoctor(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Getting all details for doctors " + id + " from " + url);
        String response = this.restTemplate.exchange(String.format("%s/doctors/%s", url, Long.toString(id)),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        log.info("Info about doctors: " + response);

        return "Id -  " + id + " \n Doctor Details " + response;
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public String getDoctors() {
        String url = getInstancesRun();
        log.info("Getting all doctors" + " from " + url);
        String response = this.restTemplate.exchange(String.format("%s/doctors", url),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "All doctors: \n" + response;
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.POST)
    public String createDoctor(@RequestBody String object) {
        String url = getInstancesRun();
        log.info("Posting doctor from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/doctors", url),
                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "Posted builder: \n" + response;
    }

    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.PUT)
    public String updateDoctor(@RequestBody String object, @PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Updating doctor from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/doctors/%s", url, id),
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Updated doctor: \n" + response;
    }

    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.DELETE)
    public String deleteDoctor(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Deleting doctor from " + url);
        String response = this.restTemplate.exchange(String.format("%s/doctors/%s", url, id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Deleted doctor: \n" + response;
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public String getDepartment(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Getting all details for department " + id + " from " + url);
        String response = this.restTemplate.exchange(String.format("%s/departments/%s", url, id),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        log.info("Info about department: " + response);

        return "Id -  " + id + " \n department Details " + response;
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String getDepartments() {
        String url = getInstancesRun();
        log.info("Getting all departments from " + url);
        String response = this.restTemplate.exchange(String.format("%s/departments", url),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "All departments: \n" + response;
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public String createDepartment(@RequestBody String object) {
        String url = getInstancesRun();
        log.info("Posting department from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/departments", url),
                HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
                }).getBody();

        return "All departments: \n" + response;
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.PUT)
    public String updateDepartment(@RequestBody String object, @PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Updating department from json from " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object, headers);

        String response = this.restTemplate.exchange(String.format("%s/departments/%s", url, id),
                HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Updated department: \n" + response;
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    public String deleteDepartment(@PathVariable Long id) {
        String url = getInstancesRun();
        log.info("Deleting departments from " + url);
        String response = this.restTemplate.exchange(String.format("%s/departments/%s", url, id),
                HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "Deleted departments: \n" + response;
    }
}
