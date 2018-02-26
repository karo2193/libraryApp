package com.library.proj.libraryapp.ui.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Karo2 on 2017-12-30.
 */

public class Presenter<ViewType> implements BasePresenter {

    protected ViewType view;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public final void onAttachView(ViewType view) {
        this.view = view;
        onViewTaken(view);
    }

    protected void onViewTaken(ViewType view) {

    }

    final void onDetachView() {
        view = null;
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
