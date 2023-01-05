package org.example.sedin.model;

import com.google.gson.annotations.SerializedName;

public class RegisterUsersAuthResponse {

  @SerializedName("id")
  private int id;

  @SerializedName("token")
  private String token;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return "RegisterUsersAuthResponse{" + "id = '" + id + '\'' + ",token = '" + token + '\'' + "}";
  }
}