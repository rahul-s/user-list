package com.rs.faces.webservices.wsmodels;

import com.google.gson.annotations.SerializedName;

public class WSError {

    @SerializedName("code")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("title")
    private String title;

    private boolean handled = false;

    public static WSError InvalidResponseError() {
        WSError error = new WSError();
        error.setStatusCode(ErrorCodes.WS_ERROR_CODE_INVALID_RESPONSE);
        error.setMessage(ErrorMessages.WS_ERROR_MESSAGE_INVALID_RESPONSE);
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    class ErrorCodes {

        static final int WS_ERROR_CODE_INVALID_RESPONSE = -1;

        // Handle other error codes as per requirement
    }

    class ErrorMessages {
        static final String WS_ERROR_MESSAGE_INVALID_RESPONSE = "An invalid response was received";

        // Handle other error messages as per requirement
    }
}
