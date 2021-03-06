package com.example.savorysecrets;

public class UserHelperClass {

    String user_email, user_username, first_name, last_name, phone_number, user_password;

    public UserHelperClass() {
    }

    public UserHelperClass(String user_email, String user_username, String first_name, String last_name, String phone_number, String user_password) {
        this.user_email = user_email;
        this.user_username = user_username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getPhone_number() { return phone_number; }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }
}
