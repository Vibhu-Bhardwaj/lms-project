package org.example.dtos;

import java.io.Serializable;

public class LoginResDto implements Serializable {

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
                    