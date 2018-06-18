package com.rs.faces.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BasePresenter.View {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutResourceId() != -1) {
            setContentView(getLayoutResourceId());
        }

        ButterKnife.bind(this);

        presenter = createPresenter();
        getPresenter().attachView(this);
        getPresenter().viewCreated();
    }


    @LayoutRes
    public abstract int getLayoutResourceId();

    @Override
    protected void onResume() {
        super.onResume();
    }

    public abstract P createPresenter();

    public final P getPresenter() {
        return presenter;
    }

}
