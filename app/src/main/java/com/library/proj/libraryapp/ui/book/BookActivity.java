package com.library.proj.libraryapp.ui.book;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.model.BookRequestFilters;
import com.library.proj.libraryapp.data.model.BookRequestPagination;
import com.library.proj.libraryapp.data.model.BookRequestQuery;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.BookModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class BookActivity extends BaseActivity<BookContract.View, BookPresenter>
        implements BookContract.View {

    private static final int API_BOOKS_LIMIT = 50;

    @BindView(R.id.book_rv)
    RecyclerView bookRv;

    private int currentPage = 0;

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
        createBookRequestData();
    }

    @Override
    public void createBookRequestData() {
        BookRequestData bookRequestData = new BookRequestData();
        BookRequestQuery bookRequestQuery = new BookRequestQuery();
        BookRequestFilters bookRequestFilters = new BookRequestFilters();
        BookRequestPagination bookRequestPagination = new BookRequestPagination();
        bookRequestPagination.setLimit(API_BOOKS_LIMIT);
        bookRequestPagination.setOffset(API_BOOKS_LIMIT * currentPage);
        bookRequestQuery.setFilters(bookRequestFilters);
        bookRequestQuery.setCategories(new ArrayList<Category>());
        bookRequestQuery.setPagination(bookRequestPagination);
        bookRequestData.setQuery(bookRequestQuery);
        getPresenter().getBooks(bookRequestData);
    }

    @Override
    public void setupBooksRv(List<Book> books) {
        bookRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        BookAdapter bookAdapter = new BookAdapter(books);
        bookRv.setAdapter(bookAdapter);
    }

    @Override
    public void onBooksError(Throwable throwable) {
        Toast.makeText(this, getResources().getString(R.string.books_error), Toast.LENGTH_LONG).show();
        Timber.e(throwable);
    }
}
