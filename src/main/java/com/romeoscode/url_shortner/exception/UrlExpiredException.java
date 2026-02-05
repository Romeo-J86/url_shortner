package com.romeoscode.url_shortner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.text.MessageFormat.format;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:38
 * projectName url_shortner
 **/

@ResponseStatus(HttpStatus.GONE)
public class UrlExpiredException extends RuntimeException {
    public UrlExpiredException(String message){
        super(format("URL has expired: {0}", message));
    }
}
