package com.library.proj.libraryapp.di.module;

import android.app.Activity;
import android.content.Intent;

import com.library.proj.libraryapp.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Karo2 on 2017-12-30.
 */

@Module
public class ActivityModule {

    public final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    public Intent provideIntent() {
        return activity.getIntent();
    }
}
