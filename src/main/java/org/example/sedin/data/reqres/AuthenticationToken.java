package org.example.sedin.data.reqres;

import lombok.Data;

@Data
public class AuthenticationToken {
    private String id;
    private String token;
}