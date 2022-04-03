package com.scheduler.app.login.service;

import com.scheduler.app.login.model.request.LoginRequest;
import com.scheduler.app.login.model.response.LoginResponse;

public interface LoginService {

     LoginResponse inputCredentials(LoginRequest loginRequest);
}
