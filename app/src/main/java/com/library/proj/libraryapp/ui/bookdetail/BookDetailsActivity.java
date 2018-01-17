package com.library.proj.libraryapp.ui.bookdetail;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.BookDetailsModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;

/**
 * Created by Karo2 on 2018-01-17.
 */

public class BookDetailsActivity extends BaseActivity<BookDetailsContract.View, BookDetailsPresenter>
        implements BookDetailsContract.View {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_book_details;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new BookDetailsModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }
}
