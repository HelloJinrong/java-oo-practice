package com.twu;

public class Admin {
    String admin="admin";
    String password="admin123";

    public Admin(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }

    public Admin() { }

    //判断登陆
    public boolean login (String name,String pass)
    {
        return name.equals(this.admin)&&pass.equals(this.password);
    }
}
