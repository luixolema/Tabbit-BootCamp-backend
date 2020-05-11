package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.User;

public interface IUserService {
    User findByLogin(String login);
}
