package com.ProductService.com.Entity;


import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document
public class User {
    @Id
    @Field("_id")
    public String user_id;
    @NonNull
    @Indexed(unique = true)
    public String username;

    @NonNull
    public String password;

    @NonNull
    @Digits(fraction = 0, integer = 10)
    public String phone;


    public String email;

    public String address;
    @NonNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    public Date dob;
    public LocalDateTime created_at;
    public LocalDateTime updated_at;
    private List<String> roles;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

