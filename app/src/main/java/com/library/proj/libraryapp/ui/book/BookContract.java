package com.library.proj.libraryapp.ui.book;

import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.ui.base.BasePresenter;
import com.library.proj.libraryapp.ui.base.BaseView;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Karo2 on 2017-12-31.
 */

public interface BookContract {
    interface Presenter extends BasePresenter {
        void getBooks(BookRequestData bookRequestData);
    }

    interface View extends BaseView {

        void createBookRequestData();

        void setupBooksRv(List<Book> books);

        void onBooksError(Throwable throwable);
    }
}
