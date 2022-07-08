package com.tech.test.utils;

import java.util.Map;

public class TestConstants {

    // Authorization
    public static final String USER_TOKEN = "User-Token";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final Map<String, String> LOGIN_CREDENTIALS = Map.of(
            LOGIN, "ibarry",
            PASSWORD, "test123");

    // Rest-Assured
    public static final String HTTP_RESPONSE = "http-response";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SESSION = "session";

    // Quotes
    public static final String QUOTE_ID = "quote_id";
    public static final String MARK = "mark";
    public static final String FAV = "fav";
    public static final String UNFAV = "unfav";

    // Response Fields
    public static final String QUOTES = "quotes";
    public static final String ID = "id";
    public static final String USER_DETAILS_FAVORITE = "user_details.favorite";
    public static final String PAGE = "page";
    public static final String LAST_PAGE = "last_page";
    public static final String QUOTES_AUTHOR = "quotes.author";
    public static final String QUOTES_ID = "quotes[0].id";
    public static final String QUOTES_FAVORITES_COUNT = "quotes[0].favorites_count";
    public static final String QUOTES_FAVORITE = "quotes[0].favorite";
    public static final String QUOTES_DIALOGUE = "quotes[0].dialogue";
    public static final String QUOTES_BODY = "quotes[0].body";
    public static final String QUOTES_TAGS = "quotes[0].tags";
}
