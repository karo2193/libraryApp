package com.library.proj.libraryapp.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.proj.libraryapp.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Karo2 on 2018-01-21.
 */

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryViewHolder> {

    private List<String> dictionaryItems = new ArrayList<>();
    private PublishSubject<String> onDictionaryItemClickSubject = PublishSubject.create();

    public DictionaryAdapter(List<String> dictionaryItems) {
        this.dictionaryItems = dictionaryItems;
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dictionary_item, parent, false);
        return new DictionaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DictionaryViewHolder holder, int position) {
        String dictionaryItem = dictionaryItems.get(position);
        onBind(holder, dictionaryItem);
    }

    @Override
    public int getItemCount() {
        return dictionaryItems.size();
    }

    private void onBind(DictionaryViewHolder holder, String dictionaryItem) {
        holder.nameTv.setText(dictionaryItem);
        holder.nameTv.setOnClickListener(v -> onDictionaryItemClickSubject.onNext(dictionaryItem));
    }

    public PublishSubject<String> getOnDictionaryItemClickSubject() {
        return onDictionaryItemClickSubject;
    }
}
