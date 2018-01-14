package com.library.proj.libraryapp.ui.book;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.ui.base.Presenter;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class BookPresenter extends Presenter<BookContract.View> implements BookContract.Presenter {
    private final UseCaseFactory useCaseFactory;

    public BookPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }
}
