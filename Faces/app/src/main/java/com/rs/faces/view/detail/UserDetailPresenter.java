package com.rs.faces.view.detail;

import com.rs.faces.view.base.BasePresenter;

public class UserDetailPresenter<V extends UserDetailPresenter.UserDetailView> extends BasePresenter<V> {

    @Override
    protected void viewCreated() {
        super.viewCreated();
    }

    public interface UserDetailView extends View{
        void populateViewDetails();
    }
}
