package com.tujuhsembilan.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

  @GetMapping("/data-a")
  public ResponseEntity<?> getDataA(Authentication auth) {
    if (!getAuthorities(auth).contains("ROLE_B")) {
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @GetMapping("/data-b")
  public ResponseEntity<?> getDataB(Authentication auth) {
    if (!getAuthorities(auth).contains("ROLE_A")) {
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @GetMapping("/data-c")
  public ResponseEntity<?> getDataC(Authentication auth) {
    return ResponseEntity.ok().build();
  }

  public String getAuthorities(Authentication auth) {
    Jwt jwt = (Jwt) auth.getPrincipal();
    String authorities = jwt.getClaimAsString("authorities");
    return authorities;
  }

}
