package com.romeoscode.url_shortner.util;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 19:57
 * projectName url_shortner
 **/

public interface AppDocs {
    String TITLE = "URL Shortener";
    String DESCRIPTION = "REST API for shortening long URLs and tracking click analytics.";
    String VERSION = "v1";

    interface Licence {
        String NAME = "Romeo Jerenyama";
    }

    interface Contacts {
        String EMAIL = "romeojerenyama@gmail.com";

        String NAME = "romeoscode";
    }

    interface UrlShortenerEndpoints {
        String NAME = "Url Shortener";
        String BASE_URL = "/api/urls";
        String DESCRIPTION = "Endpoints for managing url shortener";
    }
}
