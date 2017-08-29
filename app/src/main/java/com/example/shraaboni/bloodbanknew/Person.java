package com.example.shraaboni.bloodbanknew;

/**
 * Created by shraaboni on 8/2/2017.
 */

public class Person {
    private String name;
    private String Mobile;
    private String bloodtype;
    private String gender;
    private String address;
    private String lastbleedingdate;
    private String email;
    private String password;
    private String UserKey;

    public Person(String name, String mobile, String bloodtype, String gender, String address, String lastbleedingdate, String email, String password, String userKey) {
        this.name = name;
        Mobile = mobile;
        this.bloodtype = bloodtype;
        this.gender = gender;
        this.address = address;
        this.lastbleedingdate = lastbleedingdate;
        this.email = email;
        this.password = password;
        UserKey = userKey;
    }

    public Person(String name, String bloodtype, String gender, String email, String password, String userKey) {
        this.name = name;
        this.bloodtype = bloodtype;
        this.gender = gender;
        this.email = email;
        this.password = password;
        UserKey = userKey;
    }

    public Person(String name, String mobile, String bloodtype, String gender, String address, String lastbleedingdate, String email, String userKey) {
        this.name = name;
        Mobile = mobile;
        this.bloodtype = bloodtype;
        this.gender = gender;
        this.address = address;
        this.lastbleedingdate = lastbleedingdate;
        this.email = email;
        UserKey = userKey;
    }

    public Person(String mobile, String address, String lastbleedingdate) {
        Mobile = mobile;
        this.address = address;
        this.lastbleedingdate = lastbleedingdate;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getLastbleedingdate() {
        return lastbleedingdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserKey() {
        return UserKey;
    }
}
