package com.srh.api.service;

import com.srh.api.error.exception.InvalidLoginUserException;
import com.srh.api.error.exception.InvalidPasswordUserException;
import com.srh.api.model.Admin;
import com.srh.api.model.Evaluator;
import com.srh.api.utils.PasswordUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginClientService {
    @Autowired
    EvaluatorService evaluatorService;

    @Autowired
    AdminService adminService;

    PasswordUtil<Evaluator> passwordUtilEvaluator = new PasswordUtil<>();
    PasswordUtil<Admin> passwordUtilAdmin = new PasswordUtil<>();

    @SneakyThrows
    public Boolean verifyEvaluators(String login, String rawPassword) {
        Evaluator evaluator;

        try {
            evaluator = evaluatorService.findByLogin(login);
        } catch (ObjectNotFoundException e) {
            throw new InvalidLoginUserException();
        }

        if (!passwordUtilEvaluator.isEqualsPasswords(rawPassword, evaluator.getPassword())) {
            throw new InvalidPasswordUserException();
        }

        return true;
    }

    @SneakyThrows
    public Boolean generateLoginTokenByAdmins(String login, String rawPassword) {
        Admin admin;

        try {
            admin = adminService.findByLogin(login);
        } catch (ObjectNotFoundException e) {
            throw new InvalidLoginUserException();
        }

        if (!passwordUtilEvaluator.isEqualsPasswords(rawPassword, admin.getPassword())) {
            throw new InvalidPasswordUserException();
        }

        return true;
    }

    @SneakyThrows
    public Integer getAdminId(String login) {
        try {
            Admin admin = adminService.findByLogin(login);
            return admin.getId();
        } catch (ObjectNotFoundException e) {
            throw new InvalidLoginUserException();
        }
    }

    @SneakyThrows
    public Integer getEvaluatorId(String login) {
        try {
            Evaluator evaluator = evaluatorService.findByLogin(login);
            return evaluator.getId();
        } catch (ObjectNotFoundException e) {
            throw new InvalidLoginUserException();
        }
    }
}
