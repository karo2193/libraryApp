package com.library.proj.libraryapp.ui.category;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.ui.base.Presenter;

import io.reactivex.disposables.Disposable;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class CategoryPresenter extends Presenter<CategoryContract.View> implements CategoryContract.Presenter {

    private final UseCaseFactory useCaseFactory;

    public CategoryPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void getAllCategories() {
        Disposable disposable = useCaseFactory.getAllCategoriesUseCase()
                .execute()
                .subscribe(view::processCategories, view::onAllCategoriesError);
        compositeDisposable.add(disposable);
    }
}
