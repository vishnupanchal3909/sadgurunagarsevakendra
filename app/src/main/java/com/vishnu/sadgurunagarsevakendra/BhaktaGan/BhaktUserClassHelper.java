package com.vishnu.sadgurunagarsevakendra.BhaktaGan;

public class BhaktUserClassHelper {

    String name,mobile,jnumber,home,sex;

    public BhaktUserClassHelper() {
    }

    public BhaktUserClassHelper(String name, String mobile, String jnumber, String home, String sex) {
        this.name = name;
        this.mobile = mobile;
        this.jnumber = jnumber;
        this.home = home;
        this.sex = sex;
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

    public String getJnumber() {
        return jnumber;
    }

    public void setJnumber(String jnumber) {
        this.jnumber = jnumber;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
