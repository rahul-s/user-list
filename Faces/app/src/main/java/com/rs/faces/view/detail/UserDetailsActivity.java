package com.rs.faces.view.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rs.faces.R;
import com.rs.faces.models.User;
import com.rs.faces.view.base.BaseActivity;

import butterknife.Bind;

public class UserDetailsActivity extends BaseActivity<UserDetailPresenter> implements UserDetailPresenter.UserDetailView {

    public static final String PARAM_USER = "user";

    @Bind(R.id.user_detail_avatar)
    ImageView userAvatar;
    @Bind(R.id.user_detail_name)
    TextView userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.acitivity_user_details;
    }

    @Override
    public UserDetailPresenter createPresenter() {

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(PARAM_USER)) {
            User user = ((User) getIntent().getExtras().getSerializable(PARAM_USER));
            return new UserDetailPresenter(user);
        }
        else {
            finish();
            return null;
        }
    }

    @Override
    public void populateViewDetails(User user) {

        // User image
        Glide.with(userAvatar.getContext())
                .load(user.getAvatarUrl())
                .error(R.drawable.ic_user_icon)
                .placeholder(R.drawable.ic_user_icon)
                .into(userAvatar);

        // User name
        userName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));

        setTitle(user.getFirstName());
    }
}
