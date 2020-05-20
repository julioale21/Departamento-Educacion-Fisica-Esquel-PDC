package com.example.departamentoeducacionfisicaesquel.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String name = "";
    private String email = "";
    private Boolean isAdmin = false;
    private static User user;

    private User(){

    }

    public static User getInstance(){
        if (user == null){
            user = new User();
        }
        return user;
    }

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

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
