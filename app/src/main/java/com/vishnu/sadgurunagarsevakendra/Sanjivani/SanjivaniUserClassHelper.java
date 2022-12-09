package com.vishnu.sadgurunagarsevakendra.Sanjivani;

public class SanjivaniUserClassHelper {

    String name,mobile,amount;

    public SanjivaniUserClassHelper() {
    }

    public SanjivaniUserClassHelper(String name, String mobile, String amount) {
        this.name = name;
        this.mobile = mobile;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
