package com.library.proj.libraryapp.ui.search;

import android.os.Bundle;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.MainModule;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenter>
        implements SearchContract.View {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new SearchModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
