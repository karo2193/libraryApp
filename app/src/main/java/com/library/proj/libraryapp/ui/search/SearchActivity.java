package com.library.proj.libraryapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.BookRequestFilters;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.data.model.CategoryResponse;
import com.library.proj.libraryapp.data.model.Dictionary;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.book.BookActivity;
import com.library.proj.libraryapp.ui.category.CategoryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

import static com.library.proj.libraryapp.ui.category.CategoryActivity.ON_BACK_CATEGORIES_EXTRA;
import static com.library.proj.libraryapp.ui.category.CategoryActivity.ON_BACK_RESULT_CODE;
import static com.library.proj.libraryapp.ui.search.DictionaryDialog.BOOK_AVAILABILITY_MODE;
import static com.library.proj.libraryapp.ui.search.DictionaryDialog.BOOK_TYPES_MODE;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenter>
        implements SearchContract.View {

    private static final int YEAR_LENGTH = 4;
    public static final String BOOK_FILTERS_EXTRA = "bookFiltersExtra";
    public static final String BOOK_CATEGORIES_EXTRA = "bookCategoriesExtra";
    public static final String CATEGORY_LIST_EXTRA = "categoryListExtra";

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_title_et)
    EditText searchTitleEt;
    @BindView(R.id.search_author_et)
    EditText searchAuthorEt;
    @BindView(R.id.search_inventory_number_et)
    EditText searchInventoryNumberEt;
    @BindView(R.id.search_signature_et)
    EditText searchSignatureEt;
    @BindView(R.id.search_main_signature_et)
    EditText searchMainSignatureEt;
    @BindView(R.id.search_year_et)
    EditText searchYearEt;
    @BindView(R.id.search_volume_et)
    EditText searchVolumeEt;
    @BindView(R.id.search_type_btn)
    Button searchTypeBtn;
    @BindView(R.id.search_availability_btn)
    Button searchAvailabilityBtn;
    @BindView(R.id.search_category_btn)
    Button searchCategoryBtn;

    @BindViews({R.id.search_title_et, R.id.search_author_et, R.id.search_inventory_number_et,
            R.id.search_signature_et, R.id.search_main_signature_et, R.id.search_year_et,
            R.id.search_volume_et})
    List<EditText> allEtFields;

    private String selectedType;
    private String selectedAvailability;
    private Dictionary dictionary;
    private ArrayList<Category> categories = new ArrayList<>();
    private List<String> selectedCategoriesIds = new ArrayList<>();

    @OnClick(R.id.search_refresh_iv)
    public void onRefreshClick() {
        downloadData();
    }

    @OnClick(R.id.search_delete_iv)
    public void onClearClick() {
        clearAllFields();
    }

    @OnClick(R.id.search_type_btn)
    public void onTypeClick() {
        if (isDictionaryTypeEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.search_no_directory_items), Toast.LENGTH_LONG).show();
        } else {
            openDictionaryDialogInTypesMode();
        }
    }

    @OnClick(R.id.search_availability_btn)
    public void onAvailabilityClick() {
        if (isDictionaryAvailabilityEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.search_no_directory_items), Toast.LENGTH_LONG).show();
        } else {
            openDictionaryDialogInAvailabilityMode();
        }
    }

    @OnClick(R.id.search_category_btn)
    public void onCategoryClick() {
        if(categories.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.search_no_directory_items), Toast.LENGTH_LONG).show();
        } else {
            openCategoryActivity();
        }
    }

    @OnClick(R.id.search_btn)
    public void onSearchClick() {
        if (allFieldsAreFillCorrectly()) {
            BookRequestFilters bookRequestFilters = getBookRequestFilters();
            String[] selectedCategories = selectedCategoriesIds
                    .toArray(new String[selectedCategoriesIds.size()]);
            Intent intent = new Intent(this, BookActivity.class);
            intent.putExtra(BOOK_FILTERS_EXTRA, bookRequestFilters);
            intent.putExtra(BOOK_CATEGORIES_EXTRA, selectedCategories);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        downloadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ON_BACK_RESULT_CODE && resultCode == RESULT_OK && data != null) {
            categories = data.getParcelableArrayListExtra(ON_BACK_CATEGORIES_EXTRA);
            setCategoryButtonText();
        }
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new SearchModule()).inject(this);
    }

    @Override
    public void processDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void onDictionaryError(Throwable throwable) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.dictionary_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void processCategories(List<CategoryResponse> categoryResponses) {
        categories.clear();
        for(CategoryResponse categoryResponse : categoryResponses) {
            categoryResponse.setCategorySubcategories();
            categories.add(categoryResponse.getCategory());
        }
    }

    @Override
    public void onAllCategoriesError(Throwable throwable) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.categories_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    private void downloadData() {
        getPresenter().getDictionary();
        getPresenter().getAllCategories();
    }

    private void clearAllFields() {
        clearAllEtFields();
        clearAllButtons();
    }

    private void clearAllEtFields() {
        for (EditText editText : allEtFields) {
            editText.getText().clear();
        }
    }

    private void clearAllButtons() {
        clearDictionaryItems();
        clearCategories();
    }

    private void clearDictionaryItems() {
        selectedType = "";
        searchTypeBtn.setText(getResources().getString(R.string.search_type));
        selectedAvailability = "";
        searchAvailabilityBtn.setText(getResources().getString(R.string.search_availability));
    }

    private void clearCategories() {
        selectedCategoriesIds.clear();
        for(Category category : categories) {
            category.setChecked(false);
            category.setExpanded(false);
            clearSubcategories(category);
        }
        searchCategoryBtn.setText(getResources().getString(R.string.search_category));
    }

    private void clearSubcategories(Category category) {
        for(Category subcategory: category.getSubcategories()) {
            subcategory.setChecked(false);
        }
    }

    private BookRequestFilters getBookRequestFilters() {
        BookRequestFilters bookRequestFilters = new BookRequestFilters();
        bookRequestFilters.setTitle(searchTitleEt.getText().toString().trim());
        bookRequestFilters.setResponsibility(searchAuthorEt.getText().toString().trim());
        bookRequestFilters.setIsbnWithIssn(searchInventoryNumberEt.getText().toString().trim());
        bookRequestFilters.setFacultySignature(searchSignatureEt.getText().toString().trim());
        bookRequestFilters.setMainSignature(searchMainSignatureEt.getText().toString().trim());
        bookRequestFilters.setYear(searchYearEt.getText().toString().trim());
        bookRequestFilters.setVolume(searchVolumeEt.getText().toString().trim());
        bookRequestFilters.setType(selectedType);
        bookRequestFilters.setAvailability(selectedAvailability);
        return bookRequestFilters;
    }

    private void openDictionaryDialogInTypesMode() {
        DictionaryDialog dictionaryDialog = new DictionaryDialog(this,
                dictionary.getBookTypesList(), BOOK_TYPES_MODE);
        dictionaryDialog.setOnDictionaryDialogResult(result -> {
            selectedType = result;
            searchTypeBtn.setText(result);
        });
        dictionaryDialog.show();
    }

    private void openDictionaryDialogInAvailabilityMode() {
        DictionaryDialog dictionaryDialog = new DictionaryDialog(this,
                dictionary.getBookAvailabilitiesList(), BOOK_AVAILABILITY_MODE);
        dictionaryDialog.setOnDictionaryDialogResult(result -> {
            selectedAvailability = result;
            searchAvailabilityBtn.setText(result);
        });
        dictionaryDialog.show();
    }

    private void openCategoryActivity() {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putParcelableArrayListExtra(CATEGORY_LIST_EXTRA, categories);
        startActivityForResult(intent, ON_BACK_RESULT_CODE);
    }

    private void setCategoryButtonText() {
        setupSelectedCategories();
        if(selectedCategoriesIds.isEmpty()) {
            searchCategoryBtn.setText(getResources().getString(R.string.search_category));
        } else {
            searchCategoryBtn.setText(getResources()
                    .getString(R.string.search_category_selected, selectedCategoriesIds.size()));
        }
    }

    private void setupSelectedCategories() {
        selectedCategoriesIds.clear();
        for(Category category : categories) {
            if(category.isChecked()) {
                selectedCategoriesIds.add(category.getCategoryId());
            }
            setupSelectedSubcategories(category);
        }
    }

    private void setupSelectedSubcategories(Category category) {
        for(Category subcategory : category.getSubcategories()) {
            if(subcategory.isChecked()) {
                selectedCategoriesIds.add(subcategory.getCategoryId());
            }
        }
    }

    private boolean isDictionaryTypeEmpty() {
        return dictionary == null || dictionary.getBookTypesList() == null
                || dictionary.getBookTypesList().isEmpty();
    }

    private boolean isDictionaryAvailabilityEmpty() {
        return dictionary == null || dictionary.getBookAvailabilitiesList() == null
                || dictionary.getBookAvailabilitiesList().isEmpty();
    }

    private boolean allFieldsAreFillCorrectly() {
        if (searchYearEt.getText().length() != 0 && searchYearEt.getText().length() != YEAR_LENGTH) {
            searchYearEt.requestFocus();
            Toast.makeText(this, getResources().getString(R.string.search_wrong_year), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
