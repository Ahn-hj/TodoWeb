package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserEntity create(final UserEntity userEntity) {
    if(userEntity == null || userEntity.getUsername() == null ) {
      throw new RuntimeException("Invalid arguments");
    }
    final String username = userEntity.getUsername();
    if(userRepository.existsByUsername(username)) {
      log.warn("Username already exists {}", username);
      throw new RuntimeException("Username already exists");
    }
    
    //비밀번호암호
    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

    return userRepository.save(userEntity);
  }

  public UserEntity getByCredentials(final String username, final String password) {
	    final UserEntity originalUser = userRepository.findByUsername(username);

	    // 패스워드 같은지 확인
	    if(originalUser != null &&
	        passwordEncoder.matches(password, originalUser.getPassword())) {
	      return originalUser;
	    }
	    return null;
	  }
	}