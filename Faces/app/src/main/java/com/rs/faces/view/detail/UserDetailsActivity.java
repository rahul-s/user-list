package com.rs.faces.view.detail;

import com.rs.faces.R;
import com.rs.faces.view.base.BaseActivity;

public class UserDetailsActivity extends BaseActivity<UserDetailPresenter> implements UserDetailPresenter.UserDetailView {

    @Override
    public int getLayoutResourceId() {
        return R.layout.acitivity_user_details;
    }

    @Override
    public UserDetailPresenter createPresenter() {
        return new UserDetailPresenter();
    }

    @Override
    public void populateViewDetails() {

        // Populate the Views
    }
}
