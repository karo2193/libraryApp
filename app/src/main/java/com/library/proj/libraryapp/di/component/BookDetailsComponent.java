package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.BookDetailsModule;
import com.library.proj.libraryapp.di.module.BookModule;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.book.BookActivity;
import com.library.proj.libraryapp.ui.bookdetail.BookDetailsActivity;

import dagger.Subcomponent;

/**
 * Created by Karo2 on 2017-12-31.
 */

@ScreenScope
@Subcomponent(modules = BookDetailsModule.class)
public interface BookDetailsComponent {
    void inject(BookDetailsActivity activity);
}
