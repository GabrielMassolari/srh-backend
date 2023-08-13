package com.srh.api.utils;

import com.srh.api.error.exception.NotEqualsPasswordException;
import com.srh.api.error.exception.OldPasswordNotFoundException;
import com.srh.api.model.User;
import lombok.SneakyThrows;

import java.util.regex.Pattern;

public class PasswordUtil<T extends User> {
    public T encodedPasswordForUser(T user) {
        if (!user.getPassword().startsWith("$2a$")) {
            String encodedPassword = BcriptyUtil.encripty(user.getPassword());
            user.setPassword(encodedPassword);
        }

        return user;
    }

    @SneakyThrows
    public T verifyPasswordChanges(T newUser, T oldUser, String oldRawPassword) {
        String newRawPassword = newUser.getPassword();

        if (!isEqualsPasswords(newRawPassword, oldUser.getPassword())) {
            verifyOldPassword(oldRawPassword, oldUser.getPassword());
        }

        return encodedPasswordForUser(newUser, newRawPassword);
    }

    public boolean isEqualsPasswords(String rawPassword, String encodedPassword) {
        return BcriptyUtil.compareValues(rawPassword, encodedPassword);
    }

    private T encodedPasswordForUser(T user, String newRawPassword) {
        String encodedPassword = BcriptyUtil.encripty(newRawPassword);
        user.setPassword(encodedPassword);
        return user;
    }

    @SneakyThrows
    private void verifyOldPassword(String oldRawPassword, String oldPassword) {
        if (oldRawPassword == null) {
            throw new OldPasswordNotFoundException("Informe o valor oldPassword com a senha " +
                    "antiga do usuário");
        }

        if (!isEqualsPasswords(oldRawPassword, oldPassword)) {
            throw new NotEqualsPasswordException("A senha antiga não confere com a cadastrada");
        }
    }
}
