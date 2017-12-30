package com.library.proj.libraryapp.ui.main;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.ui.base.Presenter;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {
    private final UseCaseFactory useCaseFactory;

    public MainPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }
}
