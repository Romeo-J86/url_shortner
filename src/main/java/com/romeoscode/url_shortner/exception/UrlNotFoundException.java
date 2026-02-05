package com.romeoscode.url_shortner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.text.MessageFormat.format;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:37
 * projectName url_shortner
 **/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message){
        super(format("URL not found with short code: {0}", message));
    }
}
