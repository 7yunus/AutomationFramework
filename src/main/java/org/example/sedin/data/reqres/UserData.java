package org.example.sedin.data.reqres;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserData {

    public String name;
    public String job;
}