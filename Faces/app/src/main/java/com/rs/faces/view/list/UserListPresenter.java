package com.rs.faces.view.list;

import com.rs.faces.managers.UsersManager;
import com.rs.faces.models.User;
import com.rs.faces.view.base.BasePresenter;
import com.rs.faces.webservices.wsmodels.WSError;

import java.util.List;

public class UserListPresenter<V extends UserListPresenter.UserListView> extends BasePresenter<V> {

    @Override
    protected void viewCreated() {
        super.viewCreated();

        if (isViewAttached()) {
            getView().showLoadingIndicator();
            requestUsers(1, 4);
        }
    }

    public void requestUsers(int page, int pageSize) {
        UsersManager.getSharedInstance().fetchUsers(page, pageSize, new UsersManager.FetchUsersListener() {
            @Override
            public void usersFetched(int page, int pageSize, List<User> users, boolean endOfList) {

            }

            @Override
            public void usersFetchFailed(WSError error) {

            }
        });
    }

    public interface UserListView extends View{
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void populateUsersList();
    }
}
