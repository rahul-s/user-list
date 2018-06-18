package com.rs.faces.webservices;

public interface WebServiceSuccessListener<T> {

    void successResponse(T response);
}
