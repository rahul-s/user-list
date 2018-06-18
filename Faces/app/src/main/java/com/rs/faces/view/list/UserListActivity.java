package com.rs.faces.view.list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rs.faces.R;
import com.rs.faces.view.base.BaseActivity;

import butterknife.Bind;

public class UserListActivity extends BaseActivity<UserListPresenter>
        implements UserListPresenter.UserListView,
        UserListAdapter.UsersListListener
{

    @Bind(R.id.users_list)
    protected RecyclerView usersList;

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
        UserListAdapter adapter = new UserListAdapter();
        adapter.setListener(this);
        usersList.setAdapter(adapter);
    }

    //region View Interface methods
    @Override
    public void showLoadingIndicator() {
        // TODO: show loading indicator
    }

    @Override
    public void hideLoadingIndicator() {
        // TODO: hide loading indicator
    }

    @Override
    public void populateUsersList() {
        // TODO: populate the users in the list
    }
    //endregion


    //region List interface methods
    @Override
    public void fetchNextPage(int page, int pageSize) {
        getPresenter().requestUsers(page, pageSize);
    }

    @Override
    public void userClicked() {
        // TODO: Open user details screen
    }
    //endregion
}
