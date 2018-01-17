package com.library.proj.libraryapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.MainModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.search.SearchActivity;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenter>
        implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        openSearchActivity();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new MainModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    private void openSearchActivity() {
        Handler handler = new Handler();
        handler.postDelayed(() -> startActivity(new Intent(MainActivity.this,
                SearchActivity.class)), 1500);
    }
}
