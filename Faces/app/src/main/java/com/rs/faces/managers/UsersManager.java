package com.rs.faces.managers;

import com.rs.faces.models.User;
import com.rs.faces.webservices.UserWebService;
import com.rs.faces.webservices.WebServiceErrorListener;
import com.rs.faces.webservices.WebServiceSuccessListener;
import com.rs.faces.webservices.wsmodels.UserFetchResponse;
import com.rs.faces.webservices.wsmodels.WSError;

import java.util.List;

public class UsersManager {

    private static UsersManager sharedInstance;

    private UsersManager() {

    }

    public synchronized static UsersManager getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new UsersManager();
        }
        return sharedInstance;
    }

    public void fetchUsers(int page, int pageSize, FetchUsersListener listener) {
        new UserWebService().fetchUsers(page, pageSize, response -> {
            if (listener != null) {
                boolean endOfList = false;
                if (response.getUsers() == null || response.getUsers().size() == 0) endOfList = true;
                listener.usersFetched(response.getPage(), response.getPageSize(), response.getUsers(), endOfList);
            }
        }, error -> {
            if (listener != null) {
                listener.usersFetchFailed(error);
            }
        });
    }

    public interface FetchUsersListener {
        void usersFetched(int page, int pageSize, List<User> users, boolean endOfList);
        void usersFetchFailed(WSError error);
    }
}
