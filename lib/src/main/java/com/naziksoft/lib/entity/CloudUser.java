package com.naziksoft.lib.entity;

/**
 * Created by nazar on 01.07.17.
 */

public class CloudUser {
    private String id;
    private CloudAPI api;
    private String userName;
    private String userEmail;
    private String icon;


    public CloudUser(String id, CloudAPI api, String userName, String userEmail, String icon) {
        this.id = id;
        this.api = api;
        this.userName = userName;
        this.userEmail = userEmail;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CloudAPI getApi() {
        return api;
    }

    public void setApi(CloudAPI api) {
        this.api = api;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "CloudUser{" +
                "id='" + id + '\'' +
                ", api=" + api +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
