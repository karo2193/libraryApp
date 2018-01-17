package com.library.proj.libraryapp.ui.search;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.ui.base.Presenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class SearchPresenter extends Presenter<SearchContract.View> implements SearchContract.Presenter {
    private final UseCaseFactory useCaseFactory;

    public SearchPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void getDictionary() {
        Disposable disposable = useCaseFactory.getDictionaryUseCase()
                .execute()
                .subscribe(view::processDictionary, view::onDictionaryError);
        compositeDisposable.add(disposable);
    }
}
