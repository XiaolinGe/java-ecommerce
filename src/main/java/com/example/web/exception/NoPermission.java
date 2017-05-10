package com.example.web.exception;

/**
 * Created by gezilin on 8/05/17.
 */
public class NoPermission extends RuntimeException {

    public NoPermission(String msg) {
        super(msg);
    }
}
