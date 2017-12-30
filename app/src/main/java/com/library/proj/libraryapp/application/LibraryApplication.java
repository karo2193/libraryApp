package com.library.proj.libraryapp.application;

import android.app.Application;

/**
 * Created by Karo2 on 2017-12-30.
 */

public class LibraryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LibraryApplicationComponentInitializer.INSTANCE.initAppComponent(this);
    }
}
