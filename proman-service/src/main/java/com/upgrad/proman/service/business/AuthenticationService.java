package com.upgrad.proman.service.business;

import com.upgrad.proman.service.dao.UserDao;
import com.upgrad.proman.service.entity.UserAuthTokenEntity;
import com.upgrad.proman.service.entity.UserEntity;
import com.upgrad.proman.service.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;
import java.time.ZonedDateTime;

@Service
public class AuthenticationService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private  PasswordCryptographyProvider provider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthTokenEntity authenticate(final String username, final String password) throws AuthenticationFailedException, InvalidKeySpecException {
        UserEntity userEntity = userDao.getUserByEmail(username);
        if(userEntity == null) {
            throw new AuthenticationFailedException("ATH-102", "User Not Found");
        }

        final String encryptedPassword = provider.encrypt(password, userEntity.getSalt());
        if(encryptedPassword.equals(userEntity.getPassword())) {
            //generating JWT Token
            JwtTokenProvider tokenProvider = new JwtTokenProvider(encryptedPassword);
            UserAuthTokenEntity authEntity = new UserAuthTokenEntity();
            authEntity.setUser(userEntity);

            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            authEntity.setAccessToken(tokenProvider.generateToken(userEntity.getUuid(), now, expiresAt));

            authEntity.setLoginAt(now);
            authEntity.setCreatedAt(now);
            authEntity.setExpiresAt(expiresAt);
            authEntity.setCreatedBy("api-backend");

            userDao.createAuthToken(authEntity);
            userEntity.setLastLoginAt(now);
            userDao.updateUser(userEntity);

            return authEntity;
        }
        throw new AuthenticationFailedException("ATH-102", "Incorrect Password");
    }
}
