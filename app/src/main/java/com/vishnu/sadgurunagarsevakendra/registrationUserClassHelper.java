package com.vishnu.sadgurunagarsevakendra;

public class registrationUserClassHelper {

    String name,address,mobile,j,email,password;

    public registrationUserClassHelper() {
    }

    public registrationUserClassHelper(String name, String address, String mobile, String j, String email, String password) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.j = j;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
