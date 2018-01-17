package com.library.proj.libraryapp.data;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.usecase.GetAllCategoriesUseCase;
import com.library.proj.libraryapp.data.usecase.GetBooksUseCase;
import com.library.proj.libraryapp.data.usecase.GetDictionaryUseCase;
import com.library.proj.libraryapp.data.utils.RxTransformerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Karo2 on 2017-12-30.
 */

@Singleton
public class UseCaseFactory {

    private final DatabaseGateway databaseGateway;
    private final RxTransformerProvider rxTransformerProvider;

    @Inject
    public UseCaseFactory(DatabaseGateway databaseGateway,
                          RxTransformerProvider rxTransformerProvider) {
        this.databaseGateway = databaseGateway;
        this.rxTransformerProvider = rxTransformerProvider;
    }

    public GetBooksUseCase getBooksUseCase(BookRequestData bookRequestData) {
        return new GetBooksUseCase(rxTransformerProvider, databaseGateway, bookRequestData);
    }

    public GetAllCategoriesUseCase getAllCategoriesUseCase() {
        return new GetAllCategoriesUseCase(rxTransformerProvider, databaseGateway);
    }

    public GetDictionaryUseCase getDictionaryUseCase() {
        return new GetDictionaryUseCase(rxTransformerProvider, databaseGateway);
    }
}
