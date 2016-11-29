package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
<<<<<<< HEAD
    private String pass;

    public UserDTO(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.pass = password;
=======
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("privilege")
    @Expose
    private Integer privilege;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, Integer privilege) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.privilege = privilege;
    }

    public UserDTO(String name, String email, String password, Integer privilege) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.privilege = privilege;
>>>>>>> origin/master
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
<<<<<<< HEAD
=======

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public void setPassword(String password) {
        this.password = password;
    }
>>>>>>> origin/master
}
