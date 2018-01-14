package com.library.proj.libraryapp.di.module;

import android.app.Application;
import android.content.Context;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.remote.DatabaseRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Karo2 on 2017-12-30.
 */

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    @Named("SubscribeOnScheduler")
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ObserveOnScheduler")
    public Scheduler provideAndroidMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public DatabaseGateway provideDatabaseGateway(DatabaseRepository databaseRepository) {
        return databaseRepository;
    }
}
