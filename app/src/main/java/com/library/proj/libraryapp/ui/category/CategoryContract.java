package com.library.proj.libraryapp.ui.category;

import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.data.model.CategoryResponse;
import com.library.proj.libraryapp.ui.base.BasePresenter;
import com.library.proj.libraryapp.ui.base.BaseView;

import java.util.List;

/**
 * Created by Karo2 on 2017-12-31.
 */

public interface CategoryContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView {
        void setupCategoriesLv();
    }
}
