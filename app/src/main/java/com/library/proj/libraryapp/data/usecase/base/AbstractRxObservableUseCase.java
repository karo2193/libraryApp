package com.library.proj.libraryapp.data.usecase.base;

import com.library.proj.libraryapp.data.utils.RxTransformerProvider;

import io.reactivex.Observable;

/**
 * Created by Karo2 on 2018-01-14.
 */

public abstract class AbstractRxObservableUseCase<T> implements UseCase<Observable<T>> {

    private RxTransformerProvider rxTransformerProvider;

    public AbstractRxObservableUseCase(RxTransformerProvider rxTransformerProvider) {
        this.rxTransformerProvider = rxTransformerProvider;
    }

    public abstract Observable<T> getObservable();

    @Override
    public Observable<T> execute() {
        return getObservable().compose(rxTransformerProvider.getObservableApiSchedulers());
    }
}
