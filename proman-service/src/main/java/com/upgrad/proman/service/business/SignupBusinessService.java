package com.upgrad.proman.service.business;

import com.upgrad.proman.service.dao.UserDao;
import com.upgrad.proman.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;

@Service
public class SignupBusinessService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signup(UserEntity userEntity) throws InvalidKeySpecException {
        String[] encrytedPassowrd = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encrytedPassowrd[0]);
        userEntity.setPassword(encrytedPassowrd[1]);

        return userDao.createUser(userEntity);
    }
}
