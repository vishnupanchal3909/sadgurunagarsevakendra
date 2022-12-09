package com.vishnu.sadgurunagarsevakendra.Report;

public class ReportUserClassHelper {
    String month,years,report;

    public ReportUserClassHelper() {
    }

    public ReportUserClassHelper(String month, String years, String report) {
        this.month = month;
        this.years = years;
        this.report = report;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
