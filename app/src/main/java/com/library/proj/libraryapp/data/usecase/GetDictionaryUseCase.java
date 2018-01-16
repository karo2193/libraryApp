package com.library.proj.libraryapp.data.usecase;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.model.Dictionary;
import com.library.proj.libraryapp.data.usecase.base.AbstractRxSingleUseCase;
import com.library.proj.libraryapp.data.utils.RxTransformerProvider;

import io.reactivex.Single;

/**
 * Created by Karo2 on 2018-01-16.
 */

public class GetDictionaryUseCase extends AbstractRxSingleUseCase<Dictionary> {

    private DatabaseGateway databaseGateway;

    public GetDictionaryUseCase(RxTransformerProvider rxTransformerProvider,
                                DatabaseGateway databaseGateway) {
        super(rxTransformerProvider);
        this.databaseGateway = databaseGateway;
    }

    @Override
    public Single<Dictionary> getSingle() {
        return Single.defer(() -> databaseGateway.getDictionary());
    }
}
