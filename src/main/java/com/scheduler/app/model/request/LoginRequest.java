package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {

    @Getter
    @Setter
    private String userID;

    @Getter
    @Setter
    private String password;

}
