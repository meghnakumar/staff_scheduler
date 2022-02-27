package com.scheduler.app.service;

import com.scheduler.app.model.request.LoginRequest;
import com.scheduler.app.model.response.LoginResponse;

public interface LoginService {

    public LoginResponse inputCredentials(LoginRequest loginRequest);
}
