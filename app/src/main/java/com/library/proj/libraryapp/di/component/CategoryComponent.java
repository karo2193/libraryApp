package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.CategoryModule;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.category.CategoryActivity;

import dagger.Subcomponent;

/**
 * Created by Karo2 on 2017-12-31.
 */

@ScreenScope
@Subcomponent(modules = CategoryModule.class)
public interface CategoryComponent {
    void inject(CategoryActivity activity);
}
