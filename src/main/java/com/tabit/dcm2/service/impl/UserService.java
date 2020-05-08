package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IUserRepo;
import com.tabit.dcm2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login).orElseThrow(ResourceNotFoundException::new);
    }
}