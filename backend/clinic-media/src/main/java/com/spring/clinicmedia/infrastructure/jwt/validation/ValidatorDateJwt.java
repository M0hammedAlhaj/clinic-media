package com.spring.clinicmedia.infrastructure.jwt.validation;

import com.spring.clinicmedia.infrastructure.jwt.BaseHandler;
import com.spring.clinicmedia.infrastructure.jwt.claim.ExtractClaimDate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ValidatorDateJwt extends BaseHandler<String> {

    private final ExtractClaimDate extractClaimDate;

    public ValidatorDateJwt(ExtractClaimDate extractClaimDate) {
        this.extractClaimDate = extractClaimDate;
    }

    @Override
    public void handle(String token) {
        Date date = extractClaimDate.execute(token);
        if (date.before(new Date())) {
            throw new RuntimeException("Date is out of date");
        }
    }

}
