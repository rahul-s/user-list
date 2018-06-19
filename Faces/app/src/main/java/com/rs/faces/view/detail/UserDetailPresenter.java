package com.rs.faces.view.detail;

import com.rs.faces.models.User;
import com.rs.faces.view.base.BasePresenter;

public class UserDetailPresenter<V extends UserDetailPresenter.UserDetailView> extends BasePresenter<V> {

    private User user;

    public UserDetailPresenter(User user) {
        this.user = user;
    }

    @Override
    protected void viewCreated() {
        if (isViewAttached()) {
            getView().populateViewDetails(user);
        }
    }

    public interface UserDetailView extends View{
        void populateViewDetails(User user);
    }
}
