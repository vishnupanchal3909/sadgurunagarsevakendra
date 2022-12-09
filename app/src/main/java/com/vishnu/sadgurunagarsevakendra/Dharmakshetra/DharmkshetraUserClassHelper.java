package com.vishnu.sadgurunagarsevakendra.Dharmakshetra;

public class DharmkshetraUserClassHelper {

    String name,mobile,startdate,enddate,jangnan;

    public DharmkshetraUserClassHelper() {
    }

    public DharmkshetraUserClassHelper(String name, String mobile, String startdate, String enddate, String jangnan) {
        this.name = name;
        this.mobile = mobile;
        this.startdate = startdate;
        this.enddate = enddate;
        this.jangnan = jangnan;
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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getJangnan() {
        return jangnan;
    }

    public void setJangnan(String jangnan) {
        this.jangnan = jangnan;
    }
}
