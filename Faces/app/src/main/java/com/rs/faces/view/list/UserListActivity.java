package com.rs.faces.view.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rs.faces.R;
import com.rs.faces.models.User;
import com.rs.faces.view.base.BaseActivity;
import com.rs.faces.view.detail.UserDetailsActivity;

import java.util.List;

import butterknife.Bind;

public class UserListActivity extends BaseActivity<UserListPresenter>
        implements UserListPresenter.UserListView,
        UserListAdapter.UsersListListener
{

    @Bind(R.id.users_list)
    protected RecyclerView usersList;

    // List Adapter
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupListView();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_user_list;
    }

    @Override
    public UserListPresenter createPresenter() {
        return new UserListPresenter();
    }

    private void setupListView() {

        // Setup layout manager for recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        usersList.setLayoutManager(layoutManager);

        // Setup recycler view adapter
        adapter = new UserListAdapter();
        adapter.setListener(this);
        usersList.setAdapter(adapter);
    }

    //region View Interface methods

    @Override
    public void populateUsersList(int page, int pageSize, List<User> users) {
        if (adapter != null) {
            adapter.addUsers(page, pageSize, users);
        }
    }
    //endregion


    //region List interface methods
    @Override
    public void fetchNextPage(int page, int pageSize) {

        // Request for next page
        getPresenter().requestUsers(page, pageSize);
    }

    @Override
    public void userClicked(User user) {

        // Open user details screen
        Intent userDetailsIntent = new Intent(this, UserDetailsActivity.class);
        userDetailsIntent.putExtra(UserDetailsActivity.PARAM_USER, user);
        startActivity(userDetailsIntent);
    }
    //endregion
}
