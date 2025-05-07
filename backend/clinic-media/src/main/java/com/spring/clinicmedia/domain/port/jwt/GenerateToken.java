package com.spring.clinicmedia.domain.port.jwt;

public interface GenerateToken {

    String generateToken(String userEmail);

}
