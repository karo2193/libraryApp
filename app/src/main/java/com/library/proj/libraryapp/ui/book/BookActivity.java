package com.library.proj.libraryapp.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.model.BookRequestFilters;
import com.library.proj.libraryapp.data.model.BookRequestPagination;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.BookModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.bookdetail.BookDetailsActivity;
import com.library.proj.libraryapp.ui.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.library.proj.libraryapp.ui.search.SearchActivity.BOOK_CATEGORIES_EXTRA;

public class BookActivity extends BaseActivity<BookContract.View, BookPresenter>
        implements BookContract.View {

    private static final int API_BOOKS_LIMIT = 50;
    public static final String BOOK_EXTRA = "bookExtra";

    @BindView(R.id.book_rv)
    RecyclerView bookRv;

    private int currentPage = 0;
    private List<Book> allBooks = new ArrayList<>();
    private BookRequestData bookRequestData = new BookRequestData();

    @OnClick(R.id.book_back_iv)
    public void onBackClick() {
        onBackPressed();
    }

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
        setupBooksRv();
        createBookRequestData();
    }

    @Override
    public void createBookRequestData() {
        bookRequestData.getQuery().setFilters(getFilters(getIntent()));
        bookRequestData.getQuery().setCategories(getCategoriesIds(getIntent()));
        setupPagination();
        getPresenter().getBooks(bookRequestData);
    }

    @Override
    public void setupBooksRv() {
        allBooks.clear();
        bookRv.setLayoutManager(new LinearLayoutManager(this));
        BookAdapter bookAdapter = new BookAdapter(allBooks);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        bookRv.addItemDecoration(divider);
        bookRv.addOnScrollListener(getOnScrollListener());
        bookRv.setAdapter(bookAdapter);
        bookAdapter.getOnBookClickSubject().subscribe(book -> {
            Intent intent = new Intent(this, BookDetailsActivity.class);
            intent.putExtra(BOOK_EXTRA, book);
            startActivity(intent);
        });
    }

    @Override
    public void refreshBooks(List<Book> books) {
        allBooks.addAll(books);
        if(bookRv.getAdapter() != null) {
            bookRv.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onBooksError(Throwable throwable) {
        Toast.makeText(this, getResources().getString(R.string.books_error), Toast.LENGTH_LONG).show();
        Timber.e(throwable);
    }

    private RecyclerView.OnScrollListener getOnScrollListener() {
         return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (isScrollable(scrollState, manager)) {
                    currentPage++;
                    setupPagination();
                    getPresenter().getBooks(bookRequestData);
                }
            }

            private boolean isScrollable(int scrollState, LinearLayoutManager layoutManager) {
                return isEndOfScrollView(layoutManager) && scrollState == SCROLL_STATE_IDLE;
            }

            private boolean isEndOfScrollView(LinearLayoutManager layoutManager) {
                return layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1;
            }
        };
    }

    private void setupPagination() {
        BookRequestPagination bookRequestPagination = new BookRequestPagination();
        bookRequestPagination.setLimit(API_BOOKS_LIMIT);
        bookRequestPagination.setOffset(API_BOOKS_LIMIT * currentPage);
        bookRequestData.getQuery().setPagination(bookRequestPagination);
    }

    private BookRequestFilters getFilters(Intent intent) {
        if (intent == null) {
            return new BookRequestFilters();
        } else {
            return intent.getParcelableExtra(SearchActivity.BOOK_FILTERS_EXTRA);
        }
    }

    private String[] getCategoriesIds(Intent intent) {
        if(intent == null) {
            return new String[0];
        } else {
            return intent.getStringArrayExtra(BOOK_CATEGORIES_EXTRA);
        }
    }
}
