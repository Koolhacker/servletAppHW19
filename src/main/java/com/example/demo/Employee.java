package com.example.demo;

public class Employee {

    private int id;
    private String name;
    private String country;
    private String email;

    private int age;

    public Employee(int id, String name, String country, String email, int age) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.email = email;
        this.age = age;
    }

//    public Employee(int id, String name, String country, String email) {
//        this.id = id;
//        this.name = name;
//        this.country = country;
//        this.email = email;
//            }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Employee() {

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
