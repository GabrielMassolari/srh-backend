package com.srh.api.builder;

import com.srh.api.dto.auth.LoginForm;

public final class LoginFormBuilder {
    private String login;
    private String password;

    private LoginFormBuilder() {
    }

    public static LoginFormBuilder aLoginForm() {
        return new LoginFormBuilder();
    }

    public LoginFormBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public LoginFormBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginForm build() {
        LoginForm loginForm = new LoginForm();
        loginForm.setLogin(login);
        loginForm.setPassword(password);
        return loginForm;
    }
}
