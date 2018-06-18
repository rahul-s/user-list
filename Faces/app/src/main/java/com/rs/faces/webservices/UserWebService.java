package com.rs.faces.webservices;

import com.rs.faces.webservices.wsmodels.UserFetchResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public class UserWebService {

    private UserNetworkInterface networkInterface;

    public UserWebService() {

        // Initiate the network interface
        networkInterface = RetrofitNetworkClient.getClient().create(UserNetworkInterface.class);
    }

    public void fetchUsers(int page, int pageSize, WebServiceSuccessListener<UserFetchResponse> successListener, WebServiceErrorListener errorListener) {

        String url = "https://reqres.in/api/users";

        // Query params
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put(Params.QUERY_PARAMS.PAGE, page);
        queryParams.put(Params.QUERY_PARAMS.PAGE_SIZE, pageSize);

        Call<UserFetchResponse> userFetchResponseCall = networkInterface.fetchUsers(url, queryParams);
        userFetchResponseCall.enqueue(new RetrofitResponseHandler<>(url, successListener, errorListener));
    }


    private interface UserNetworkInterface {

        @GET
        Call<UserFetchResponse> fetchUsers(@Url String url, @QueryMap Map<String, Object> queryParams);
    }
}
