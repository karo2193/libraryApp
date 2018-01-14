package com.library.proj.libraryapp.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.BookModule;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.category.CategoryActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookActivity extends BaseActivity<BookContract.View, BookPresenter>
        implements BookContract.View {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_book;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new BookModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);
    }


}
