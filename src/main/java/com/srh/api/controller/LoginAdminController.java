package com.srh.api.controller;

import com.srh.api.dto.resource.LoginClientForm;
import com.srh.api.service.LoginClientDto;
import com.srh.api.service.LoginClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admins/login")
public class LoginAdminController {
    @Autowired
    private LoginClientService loginClientService;

    @PostMapping
    public ResponseEntity<LoginClientDto> loginUser(@RequestBody @Valid LoginClientForm loginForm) {
        Boolean validUser = loginClientService.generateLoginTokenByAdmins(
                loginForm.getLogin(), loginForm.getPassword()
        );
        Integer adminId = loginClientService.getAdminId(loginForm.getLogin());
        return ResponseEntity.ok(new LoginClientDto(validUser, adminId));
    }
}
