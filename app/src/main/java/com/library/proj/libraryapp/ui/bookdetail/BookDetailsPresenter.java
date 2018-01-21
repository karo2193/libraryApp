package com.library.proj.libraryapp.ui.bookdetail;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.ui.base.Presenter;

/**
 * Created by Karo2 on 2018-01-17.
 */

public class BookDetailsPresenter extends Presenter<BookDetailsContract.View>
        implements BookDetailsContract.Presenter {

    private final UseCaseFactory useCaseFactory;

    public BookDetailsPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }
}
