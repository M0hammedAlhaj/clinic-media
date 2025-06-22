package com.spring.clinicmedia.infrastructure.jwt;

import com.spring.clinicmedia.domain.Handler;
import lombok.Data;

@Data
public abstract class BaseHandler<T> implements Handler<T> {

    private Handler<T> next;

    public Handler<T> setNextHandler(Handler<T> next) {
        this.next = next;
        return next;
    }

    public boolean isNextNull() {
        return next == null;
    }
}
