package com.library.proj.libraryapp.ui.search;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.ui.base.Presenter;
import com.library.proj.libraryapp.ui.main.MainContract;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class SearchPresenter extends Presenter<SearchContract.View> implements SearchContract.Presenter {
    private final UseCaseFactory useCaseFactory;

    public SearchPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }
}
