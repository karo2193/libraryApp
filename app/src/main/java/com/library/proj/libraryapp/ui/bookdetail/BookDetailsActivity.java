package com.library.proj.libraryapp.ui.bookdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.BookDetailsModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.book.BookActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

/**
 * Created by Karo2 on 2018-01-17.
 */

public class BookDetailsActivity extends BaseActivity<BookDetailsContract.View, BookDetailsPresenter>
        implements BookDetailsContract.View {

    @BindView(R.id.book_details_toolbar)
    Toolbar toolbar;
    @BindView(R.id.book_details_title_tv)
    TextView titleTv;
    @BindView(R.id.book_details_author_tv)
    TextView authorTv;
    @BindView(R.id.book_details_inventory_number_tv)
    TextView inventoryNumberTv;
    @BindView(R.id.book_details_main_signature_tv)
    TextView mainSignatureTv;
    @BindView(R.id.book_details_faculty_signature_tv)
    TextView facultySignatureTv;
    @BindView(R.id.book_details_year_tv)
    TextView yearTv;
    @BindView(R.id.book_details_volume_tv)
    TextView volumeTv;
    @BindView(R.id.book_details_type_tv)
    TextView typeTv;
    @BindView(R.id.book_details_availability_tv)
    TextView availabilityTv;
    @BindView(R.id.book_details_category_tv)
    TextView categoryTv;
    @BindView(R.id.book_details_title_ll)
    LinearLayout titleLl;
    @BindView(R.id.book_details_author_ll)
    LinearLayout authorLl;
    @BindView(R.id.book_details_inventory_number_ll)
    LinearLayout inventoryNumberLl;
    @BindView(R.id.book_details_main_signature_ll)
    LinearLayout mainSignatureLl;
    @BindView(R.id.book_details_faculty_signature_ll)
    LinearLayout facultySignatureLl;
    @BindView(R.id.book_details_year_ll)
    LinearLayout yearLl;
    @BindView(R.id.book_details_volume_ll)
    LinearLayout volumeLl;
    @BindView(R.id.book_details_type_ll)
    LinearLayout typeLl;
    @BindView(R.id.book_details_availability_ll)
    LinearLayout availabilityLl;
    @BindView(R.id.book_details_category_ll)
    LinearLayout categoryLl;

    private Book book;

    @OnClick(R.id.book_details_back_iv)
    public void onBackClick() {
        onBackPressed();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_book_details;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new BookDetailsModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getBookData();
    }

    @Override
    public void getBookData() {
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, getResources().getString(R.string.book_details_error), Toast.LENGTH_LONG).show();
        } else {
            book = intent.getParcelableExtra(BookActivity.BOOK_EXTRA);
            fillFields();
        }
    }

    @Override
    public void fillFields() {
        if (book == null) return;
        fillField(authorTv, authorLl, book.getResponsibility());
        fillField(titleTv, titleLl, book.getTitle());
        fillField(inventoryNumberTv, inventoryNumberLl, book.getIsbnWithIssn());
        fillField(mainSignatureTv, mainSignatureLl, book.getMainSignature());
        fillField(facultySignatureTv, facultySignatureLl, book.getFacultySignature().toString());
        fillField(yearTv, yearLl, book.getYear().toString());
        fillField(volumeTv, volumeLl, book.getVolume());
        fillField(typeTv, typeLl, book.getType());
        fillField(availabilityTv, availabilityLl, book.getAvailability());
        fillCategories();
    }

    private void fillField(TextView field, LinearLayout fieldLl, String text) {
        if (StringUtils.isBlank(text)) {
            fieldLl.setVisibility(GONE);
        } else {
            field.setText(text);
        }
    }

    private void fillCategories() {
        if (book.getCategories() == null || book.getCategories().isEmpty()) {
            categoryLl.setVisibility(GONE);
        } else {
            String categories = "";
            for(Category category : book.getCategories()) {
                categories = StringUtils.join(category.getName(), "\n");
            }
            categoryTv.setText(categories.trim());
        }
    }
}
