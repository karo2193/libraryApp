package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.BookModule;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.book.BookActivity;

import dagger.Subcomponent;

/**
 * Created by Karo2 on 2017-12-31.
 */

@ScreenScope
@Subcomponent(modules = BookModule.class)
public interface BookComponent {
    void inject(BookActivity activity);
}
