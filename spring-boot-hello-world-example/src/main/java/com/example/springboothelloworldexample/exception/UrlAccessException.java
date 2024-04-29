package com.example.springboothelloworldexample.exception;

import lombok.Getter;

@Getter
public class UrlAccessException extends RuntimeException {

    private String url;

    public UrlAccessException(String url) {
        this(url, null);
    }

    public UrlAccessException(String url, Throwable cause) {
        super("URL " + url + " is not accessible", cause);
        this.url = url;
    }
}
