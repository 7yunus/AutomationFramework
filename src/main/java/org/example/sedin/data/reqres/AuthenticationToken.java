package org.example.sedin.data.reqres;

import lombok.Data;

@Data
public class AuthenticationToken {
    private static String id;
    private static String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}