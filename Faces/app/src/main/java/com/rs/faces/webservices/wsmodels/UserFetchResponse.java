package com.rs.faces.webservices.wsmodels;

import com.google.gson.annotations.SerializedName;
import com.rs.faces.models.User;

import java.io.Serializable;
import java.util.List;

public class UserFetchResponse implements Serializable {

    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int pageSize;
    @SerializedName("total")
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("data")
    private List<User> users;

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<User> getUsers() {
        return users;
    }
}
