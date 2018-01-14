package com.library.proj.libraryapp.data.utils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class RxTransformerProvider {

    private final Scheduler subscriberOnScheduler;
    private final Scheduler observerOnScheduler;

    @Inject
    public RxTransformerProvider(@Named("SubscriberOnScheduler") Scheduler subscriberOnScheduler,
                                 @Named("ObserveOnScheduler") Scheduler observerOnScheduler) {
        this.subscriberOnScheduler = subscriberOnScheduler;
        this.observerOnScheduler = observerOnScheduler;
    }

    public <T> SingleTransformer<T, T> getSingleApiSchedulers() {
        return upstream -> upstream.subscribeOn(subscriberOnScheduler).observeOn(observerOnScheduler);
    }

    public <T> ObservableTransformer<T, T> getObservableApiSchedulers() {
        return upstream -> upstream.subscribeOn(subscriberOnScheduler).observeOn(observerOnScheduler);
    }

    public <T> ObservableTransformer<T, T> getObservableMainApiSchedulers() {
        return upstream -> upstream.subscribeOn(observerOnScheduler).observeOn(observerOnScheduler);
    }
}
