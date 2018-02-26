package com.library.proj.libraryapp.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.library.proj.libraryapp.application.LibraryApplicationComponentInitializer;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.component.AppComponent;
import com.library.proj.libraryapp.di.component.DaggerActivityComponent;
import com.library.proj.libraryapp.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Karo2 on 2017-12-30.
 */

public abstract class BaseActivity<ViewType extends BaseView, P extends Presenter<ViewType>>
        extends AppCompatActivity {

    @Inject
    P presenter;

    ViewType view;

    private ActivityComponent activityComponent;
    private Unbinder unbinder;

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void performFieldInjection(ActivityComponent activityComponent);

    protected abstract int getFragmentContainer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutRes() != 0) {
            setContentView(getLayoutRes());
        }
        unbinder = ButterKnife.bind(this);
        Fabric.with(this, new Crashlytics());
        performFieldInjection(getActivityComponent());
        presenter.onAttachView((ViewType) this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetachView();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //icepick
    }

    public P getPresenter() {
        return presenter;
    }

    private ActivityComponent getActivityComponent() {
        if(activityComponent == null) {
            activityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return activityComponent;
    }

    private AppComponent getAppComponent() {
        return LibraryApplicationComponentInitializer.INSTANCE.getAppComponent();
    }
}
