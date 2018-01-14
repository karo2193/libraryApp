package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.MainModule;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Karo2 on 2017-12-31.
 */

@ScreenScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
