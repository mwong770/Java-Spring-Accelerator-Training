package com.company.mariawongu1m5summative.model;

import java.util.Objects;

// Model object
// Contains getters and setters to store data retrieved using PublisherDao class
public class Publisher {

    private int publisherId;

    private String name;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String phone;

    private String email;

    // getters and setters

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // compares objects for equality
    // overrides default equals() which compares memory location
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return getPublisherId() == publisher.getPublisherId() &&
                getName().equals(publisher.getName()) &&
                getStreet().equals(publisher.getStreet()) &&
                getCity().equals(publisher.getCity()) &&
                getState().equals(publisher.getState()) &&
                getPostalCode().equals(publisher.getPostalCode()) &&
                getPhone().equals(publisher.getPhone()) &&
                getEmail().equals(publisher.getEmail());
    }

    // generates an integer code corresponding to an object
    // overrides default of assigning unique hashcode value to each object when they are created in memory
    // need to override hashCode b/c if two objects are equal by equals() method then
    // their hashcode returned by hashCode() method must be same.
    @Override
    public int hashCode() {
        return Objects.hash(getPublisherId(), getName(), getStreet(), getCity(), getState(), getPostalCode(), getPhone(), getEmail());
    }

}
