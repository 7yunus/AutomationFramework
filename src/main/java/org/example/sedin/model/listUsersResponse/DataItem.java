package org.example.sedin.model.listUsersResponse;

import com.google.gson.annotations.SerializedName;

public class DataItem {

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("id")
    private int id;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("email")
    private String email;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "last_name = '" + lastName + '\'' +
                        ",id = '" + id + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",first_name = '" + firstName + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }
}