package com.cargocompass.app.userservice.constants;

public final class UserConstants {
    private UserConstants() {
    }
    public static final String API_PREFIX="/api";
    public static final String API_VERSION_V1="/v1";
    public static final String API_USER="/users";
    public static final String API_USER_REGISTER = "/register";
    public static final String API_USER_UPDATE = "/update/{userId}";

    public static final String API_USER_FORGOT = "/forgot-password";

    public static final String API_USER_RESET = "/reset-password";

    public static final String USER_NOT_FOUND = "User not found";

    public static final String WRONG_USERNAME_OR_PASSWORD = "Wrong username or password";

    public static final String API_USER_CHANGE_PASSWORD = "/change-password";

    public static final String CURRENT_AND_BEFORE_PASSWORD_NOT_MATCH = "Current and before password not match";



}
