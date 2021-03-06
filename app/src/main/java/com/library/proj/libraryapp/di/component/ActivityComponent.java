package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.ActivityModule;
import com.library.proj.libraryapp.di.module.BookDetailsModule;
import com.library.proj.libraryapp.di.module.BookModule;
import com.library.proj.libraryapp.di.module.CategoryModule;
import com.library.proj.libraryapp.di.module.MainModule;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Karo2 on 2017-12-30.
 */

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {

    MainComponent addModule(MainModule module);
    SearchComponent addModule(SearchModule module);
    CategoryComponent addModule(CategoryModule module);
    BookComponent addModule(BookModule module);
    BookDetailsComponent addModule(BookDetailsModule module);
}
