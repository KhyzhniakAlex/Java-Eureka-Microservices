package com.labs.maven.springBoot.SpringBootMSC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fname", nullable = false)
    private String fname;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name="presenceFlag", nullable = false)
    private boolean presenceFlag = true;

    public Doctor(String fname, String surname, Integer age, Integer salary)
    {
        this.fname = fname;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
    }

    public Doctor() {

    }

    //LAZY – стратегия, которая не предусматривает получение полной связи сущностей, и при первом обращении к связи будет выполнятся запрос на получение данных с БД.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentID")
    @JsonIgnore
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }



    public void setId(int id) {
        this.id = id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public boolean getPresenceFlag() {
        return presenceFlag;
    }
    public void setPresenceFlag(boolean flag) {
        this.presenceFlag = flag;
    }

    @Override
    public boolean equals(Object obj)
    {
        Doctor doc = (Doctor)obj;
        return id == doc.id &&
                fname.equals(doc.fname) &&
                surname.equals(doc.surname) &&
                age.equals(doc.age) &&
                salary.equals(doc.salary);
    }
}
