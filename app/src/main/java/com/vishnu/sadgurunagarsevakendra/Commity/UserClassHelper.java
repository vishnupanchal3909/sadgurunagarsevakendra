package com.vishnu.sadgurunagarsevakendra.Commity;

public class UserClassHelper {
    String name,mobile,sit;

    public UserClassHelper() {
    }

    public UserClassHelper(String name, String mobile, String sit) {
        this.name = name;
        this.mobile = mobile;
        this.sit = sit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
    }
}
