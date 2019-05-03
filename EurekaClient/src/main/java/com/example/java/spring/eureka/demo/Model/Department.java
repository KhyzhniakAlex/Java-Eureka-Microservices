package com.example.java.spring.eureka.demo.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "floor", nullable = false)
    private Integer floor;

    public Department(String name, Integer floor)
    {
        this.name = name;
        this.floor = floor;
    }

    public Department() {

    }

    //Определяет набор каскадных операций, которые распространяются на соответствующие сущности: cascade=ALL – на все операции
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Doctor> doctors;

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object obj)
    {
        Department dep = (Department)obj;
        return id == dep.id &&
                name.equals(dep.name) &&
                floor.equals(dep.floor);
    }
}

