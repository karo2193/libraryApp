package com.library.proj.libraryapp.ui.search;

import com.library.proj.libraryapp.data.model.Dictionary;
import com.library.proj.libraryapp.ui.base.BasePresenter;
import com.library.proj.libraryapp.ui.base.BaseView;

/**
 * Created by Karo2 on 2017-12-31.
 */

public interface SearchContract {
    interface Presenter extends BasePresenter {
        void getDictionary();
    }

    interface View extends BaseView {
        void processDictionary(Dictionary dictionary);
        void onDictionaryError(Throwable throwable);
    }
}
