package com.library.proj.libraryapp.data.usecase.base;

import com.library.proj.libraryapp.data.utils.RxTransformerProvider;

import io.reactivex.Single;

/**
 * Created by Karo2 on 2018-01-14.
 */

public abstract class AbstractRxSingleUseCase<T> implements UseCase<Single<T>> {

    private RxTransformerProvider rxTransformerProvider;

    public AbstractRxSingleUseCase(RxTransformerProvider rxTransformerProvider) {
        this.rxTransformerProvider = rxTransformerProvider;
    }

    public abstract Single<T> getSingle();

    @Override
    public Single<T> execute() {
        return getSingle().compose(rxTransformerProvider.getSingleApiSchedulers());
    }
}
