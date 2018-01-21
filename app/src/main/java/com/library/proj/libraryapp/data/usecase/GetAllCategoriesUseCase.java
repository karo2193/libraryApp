package com.library.proj.libraryapp.data.usecase;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.model.CategoryResponse;
import com.library.proj.libraryapp.data.usecase.base.AbstractRxObservableUseCase;
import com.library.proj.libraryapp.data.utils.RxTransformerProvider;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by Karo2 on 2018-01-16.
 */

public class GetAllCategoriesUseCase extends AbstractRxObservableUseCase<List<CategoryResponse>> {

    private DatabaseGateway databaseGateway;

    public GetAllCategoriesUseCase(RxTransformerProvider rxTransformerProvider,
                                   DatabaseGateway databaseGateway) {
        super(rxTransformerProvider);
        this.databaseGateway = databaseGateway;
    }

    @Override
    public Observable<List<CategoryResponse>> getObservable() {
        return Observable.defer(() -> databaseGateway.getAllCategories());
    }
}
