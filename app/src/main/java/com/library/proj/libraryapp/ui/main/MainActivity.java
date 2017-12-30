package com.library.proj.libraryapp.ui.main;

import android.os.Bundle;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.MainModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenter>
        implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
