package com.library.proj.libraryapp.ui.category;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.CategoryModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity<CategoryContract.View, CategoryPresenter>
        implements CategoryContract.View {

    @BindView(R.id.category_lv)
    ExpandableListView categoryLv;

    private List<Category> categories = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_category;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new CategoryModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        getPresenter().getAllCategories();
    }

    @Override
    public void processCategories(List<Category> categories) {
        this.categories = categories;
        setupCategoriesLv();
    }

    @Override
    public void setupCategoriesLv() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        adapter.getOnCategoryClick().subscribe(category -> {
            int clickedCategoryPosition = categories.indexOf(category);
            if (categoryLv.isGroupExpanded(clickedCategoryPosition)) {
                categoryLv.collapseGroup(clickedCategoryPosition);
            } else {
                categoryLv.expandGroup(clickedCategoryPosition);
            }
        });
        adapter.getOnCategoryCheckBoxClick().subscribe(category
                -> category.setChecked(!category.isChecked()));
        categoryLv.setAdapter(adapter);
    }
}
