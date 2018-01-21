package com.library.proj.libraryapp.ui.search;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.library.proj.libraryapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Karo2 on 2018-01-21.
 */

public class DictionaryDialog extends Dialog {

    public static final int BOOK_TYPES_MODE = 0;
    public static final int BOOK_AVAILABILITY_MODE = 1;

    @BindView(R.id.dictionary_title_tv)
    TextView titleTv;
    @BindView(R.id.dictionary_rv)
    RecyclerView availabilityRv;

    private List<String> availabilities;
    private Activity activity;
    private int dictionaryMode;
    private OnDictionaryDialogResult onDictionaryDialogResult;

    public DictionaryDialog(Activity activity, List<String> availabilities, int dictionaryMode) {
        super(activity);
        this.availabilities = availabilities;
        this.activity = activity;
        this.dictionaryMode = dictionaryMode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dictionary_dialog);
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        setupTitle();
        setupRv();
    }

    private void setupTitle() {
        switch (dictionaryMode) {
            case BOOK_TYPES_MODE:
                titleTv.setText(activity.getResources().getString(R.string.search_type));
                break;
            case BOOK_AVAILABILITY_MODE:
                titleTv.setText(activity.getResources().getString(R.string.search_availability));
                break;
            default:
                titleTv.setText("");
                break;
        }
    }

    private void setupRv() {
        availabilityRv.setLayoutManager(new LinearLayoutManager(activity));
        DictionaryAdapter adapter = new DictionaryAdapter(availabilities);
        availabilityRv.setAdapter(adapter);
        adapter.getOnDictionaryItemClickSubject().subscribe(
                dictionary -> {
                    onDictionaryDialogResult.finish(dictionary);
                    dismiss();
                });
    }

    public void setOnDictionaryDialogResult(OnDictionaryDialogResult onDictionaryDialogResult) {
        this.onDictionaryDialogResult = onDictionaryDialogResult;
    }

    public interface OnDictionaryDialogResult {
        void finish(String selectedItem);
    }
}
