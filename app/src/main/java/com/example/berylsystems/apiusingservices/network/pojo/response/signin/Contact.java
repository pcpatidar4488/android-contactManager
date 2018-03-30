package com.example.berylsystems.apiusingservices.network.pojo.response.signin;

/**
 * Created by BerylSystems on 17-Mar-18.
 */

public class Contact {
    private String name;
    private String email;
    private String mobile;
    private String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
