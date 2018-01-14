package com.library.proj.libraryapp.application;

import android.app.Application;

import com.library.proj.libraryapp.BuildConfig;

import timber.log.Timber;

/**
 * Created by Karo2 on 2017-12-30.
 */

public class LibraryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LibraryApplicationComponentInitializer.INSTANCE.initAppComponent(this);
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
