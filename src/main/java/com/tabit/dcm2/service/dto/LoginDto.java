package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

public class LoginDto extends AbstractBean {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<LoginDto, Builder> {

        public Builder() {
            super(new LoginDto());
        }

        public Builder withLogin(String login) {
            bean.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            bean.password = password;
            return this;
        }
    }
}