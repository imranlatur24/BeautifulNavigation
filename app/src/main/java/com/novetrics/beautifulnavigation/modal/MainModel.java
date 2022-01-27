package com.novetrics.beautifulnavigation.modal;

import java.util.ArrayList;

public class MainModel {

    public String resp;
    public String empid;
    public String password;
    public String message;
    public ArrayList<User> employee_details;

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<User> getEmployee_details() {
        return employee_details;
    }

    public void setEmployee_details(ArrayList<User> employee_details) {
        this.employee_details = employee_details;
    }
}
