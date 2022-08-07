package com.example.redditclone.service;

import com.example.redditclone.dto.RegisterRequest;
import com.example.redditclone.model.NotificationEmail;
import com.example.redditclone.model.User;
import com.example.redditclone.model.VerificationToken;
import com.example.redditclone.repository.UserRepository;
import com.example.redditclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Transactional
@Service //Read Later
@Slf4j

public class AuthService {
    //Read Later => This is field injection; however, spring suggests to use constructor injection
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    //Read Later
    public void signUp(RegisterRequest registerRequest){
        User user =  new User();
        user.setUsername(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        //Read Later + how it is being called without any reference to config
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your account by clicking on the link",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
        log.info("LAst sTAGE");
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken =  new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;

    }

}
