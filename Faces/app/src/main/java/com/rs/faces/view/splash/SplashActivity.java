package com.rs.faces.view.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rs.faces.R;
import com.rs.faces.view.base.BaseActivity;
import com.rs.faces.view.base.BasePresenter;
import com.rs.faces.view.list.UserListActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(this::startListActivity, 2000);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    public BasePresenter createPresenter() {
        return new BasePresenter();
    }

    private void startListActivity() {
        Intent listIntent = new Intent(this, UserListActivity.class);
        startActivity(listIntent);
        finish();
    }
}
