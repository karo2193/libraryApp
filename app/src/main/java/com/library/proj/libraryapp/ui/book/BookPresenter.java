package com.library.proj.libraryapp.ui.book;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.ui.base.Presenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class BookPresenter extends Presenter<BookContract.View> implements BookContract.Presenter {

    private final UseCaseFactory useCaseFactory;

    public BookPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void getBooks(BookRequestData bookRequestData) {
        Disposable disposable = useCaseFactory.getBooksUseCase(bookRequestData)
                .execute()
                .subscribe(view::setupBooksRv, view::onBooksError);
        compositeDisposable.add(disposable);
    }
}
