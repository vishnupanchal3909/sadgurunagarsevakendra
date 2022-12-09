package com.vishnu.sadgurunagarsevakendra.BhaktaGan;

public class AttendanceUserClass {
    String Name,Jangana;

    public AttendanceUserClass() {
    }

    public AttendanceUserClass(String name, String jangana) {
        Name = name;
        Jangana = jangana;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getJangana() {
        return Jangana;
    }

    public void setJangana(String jangana) {
        Jangana = jangana;
    }
}
