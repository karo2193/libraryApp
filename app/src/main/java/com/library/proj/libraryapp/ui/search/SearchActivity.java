package com.library.proj.libraryapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.BookRequestFilters;
import com.library.proj.libraryapp.data.model.Dictionary;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.book.BookActivity;
import com.library.proj.libraryapp.ui.category.CategoryActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenter>
        implements SearchContract.View {

    public static final String BOOK_FILTERS_EXTRA = "bookFiltersExtra";

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_title_et)
    EditText searchTitleEt;
    @BindView(R.id.search_author_et)
    EditText searchAuthorEt;
    @BindView(R.id.search_inventory_number_et)
    EditText searchInventoryNumberEt;
    @BindView(R.id.search_main_signature_et)
    EditText searchMainSignatureEt;
    @BindView(R.id.search_year_et)
    EditText searchYearEt;
    @BindView(R.id.search_volume_et)
    EditText searchVolumeEt;
    @BindView(R.id.search_type_spn)
    Spinner searchTypeSpn;
    @BindView(R.id.search_availability_spn)
    Spinner searchAvailabilitySpn;
    @BindView(R.id.search_category_btn)
    Button searchCategoryBtn;
    @BindView(R.id.search_clear_btn)
    Button searchClearBtn;
    @BindView(R.id.search_search_btn)
    Button searchSearchBtn;
    @BindViews({R.id.search_title_et, R.id.search_author_et, R.id.search_inventory_number_et,
            R.id.search_main_signature_et, R.id.search_volume_et})
    List<EditText> allEtFields;

    private String selectedType;
    private String selectedAvailability;

    @OnClick(R.id.search_clear_btn)
    public void onClearClick() {
        clearAllFields();
    }

    @OnClick(R.id.search_category_btn)
    public void onCategoryClick() {
        startActivity(new Intent(this, CategoryActivity.class));
    }

    @OnClick(R.id.search_search_btn)
    public void onSearchClick() {
        BookRequestFilters bookRequestFilters = getBookRequestFilters();
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra(BOOK_FILTERS_EXTRA, bookRequestFilters);
        startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new SearchModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getPresenter().getDictionary();
    }

    @Override
    public void processDictionary(Dictionary dictionary) {
        setupTypeSpinner(dictionary.getBookTypesList());
        setupAvailabilitySpinner(dictionary.getBookAvailabilitiesList());
    }

    @Override
    public void onDictionaryError(Throwable throwable) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.dictionary_error), Toast.LENGTH_LONG).show();
    }

    private void setupTypeSpinner(List<String> bookTypesList) {
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, bookTypesList);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpn.setAdapter(typesAdapter);
        searchTypeSpn.setOnItemSelectedListener(getOnTypeSelectedListener());
    }

    private AdapterView.OnItemSelectedListener getOnTypeSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedType = searchTypeSpn.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    private void setupAvailabilitySpinner(List<String> bookAvailabilitiesList) {
        ArrayAdapter<String> availabilityAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, bookAvailabilitiesList);
        availabilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchAvailabilitySpn.setAdapter(availabilityAdapter);
        searchAvailabilitySpn.setOnItemSelectedListener(getOnAvailabilitySelectedListener());
    }

    private AdapterView.OnItemSelectedListener getOnAvailabilitySelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedAvailability = searchAvailabilitySpn.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    private void clearAllFields() {
        clearAllEtFields();
    }

    private void clearAllEtFields() {
        for (EditText editText : allEtFields) {
            editText.getText().clear();
        }
    }

    private BookRequestFilters getBookRequestFilters() {
        BookRequestFilters bookRequestFilters = new BookRequestFilters();
        bookRequestFilters.setTitle(searchTitleEt.getText().toString().trim());
        bookRequestFilters.setResponsibility(searchAuthorEt.getText().toString().trim());
        bookRequestFilters.setIsbnWithIssn(searchInventoryNumberEt.getText().toString().trim());
        bookRequestFilters.setMainSignature(searchMainSignatureEt.getText().toString().trim());
        bookRequestFilters.setYear(Integer.getInteger(searchYearEt.getText().toString().trim()));
        bookRequestFilters.setVolume(searchVolumeEt.getText().toString().trim());
        bookRequestFilters.setType(selectedType);
        bookRequestFilters.setAvailability(selectedAvailability);
        return bookRequestFilters;
    }
}
