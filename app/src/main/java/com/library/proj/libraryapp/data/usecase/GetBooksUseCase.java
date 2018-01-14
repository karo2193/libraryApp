package com.library.proj.libraryapp.data.usecase;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.usecase.base.AbstractRxObservableUseCase;
import com.library.proj.libraryapp.data.utils.RxTransformerProvider;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class GetBooksUseCase extends AbstractRxObservableUseCase<List<Book>> {

    private DatabaseGateway databaseGateway;
    private BookRequestData bookRequestData;

    public GetBooksUseCase(RxTransformerProvider rxTransformerProvider,
                           DatabaseGateway databaseGateway, BookRequestData bookRequestData) {
        super(rxTransformerProvider);
        this.databaseGateway = databaseGateway;
        this.bookRequestData = bookRequestData;
    }

    @Override
    public Observable<List<Book>> getObservable() {
        return Observable.defer(() -> databaseGateway.getBooks(bookRequestData));
    }
}
