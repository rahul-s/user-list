package com.rs.faces.webservices;

import com.rs.faces.webservices.wsmodels.WSError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitResponseHandler<T> implements Callback<T> {

    private WebServiceSuccessListener<T> successListener;
    private WebServiceErrorListener errorListener;
    private String webServiceURL;

    RetrofitResponseHandler(String url, WebServiceSuccessListener<T> successListener, WebServiceErrorListener errorListener) {
        webServiceURL = url;
        this.successListener = successListener;
        this.errorListener = errorListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.isSuccessful()) {
            handleSuccess(response.body());
        } else {
            WSError error = WSError.InvalidResponseError();
            handleFailure(error);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        WSError error = WSError.InvalidResponseError();
        handleFailure(error);
    }

    private void handleSuccess(T responseObject) {

        // Success callback
        if (successListener != null) {
            successListener.successResponse(responseObject);
        }
    }

    private void handleFailure(WSError error) {

        // Error callback
        if (errorListener != null) {
            errorListener.errorResponse(error);
        }
    }
}
