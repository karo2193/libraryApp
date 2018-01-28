package com.library.proj.libraryapp.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Karo2 on 2018-01-10.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {

    private List<Category> categories = new ArrayList<>();
    private PublishSubject<Category> onCategoryClick = PublishSubject.create();
    private PublishSubject<Category> onCategoryCheckBoxClick = PublishSubject.create();

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return categories.get(position).getSubcategories().size();
    }

    @Override
    public Object getGroup(int position) {
        return categories.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getSubcategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int position, boolean b, View view, ViewGroup viewGroup) {
        CategoryViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.category_item, viewGroup, false);
            viewHolder = new CategoryViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CategoryViewHolder) view.getTag();
        }
        setupCategoryViewHolder(categories.get(position), viewHolder);
        return view;
    }

    private void setupCategoryViewHolder(Category category, CategoryViewHolder viewHolder) {
        viewHolder.nameTv.setText(category.getName());
        viewHolder.itemCb.setSelected(category.isChecked());
        if (category.isExpanded()) {
            viewHolder.arrowIv.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
        } else {
            viewHolder.arrowIv.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
        viewHolder.itemCl.setOnClickListener(v -> onCategoryClick.onNext(category));
        viewHolder.itemCb.setOnClickListener(view -> onCategoryCheckBoxClick.onNext(category));
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view,
                             ViewGroup viewGroup) {
        SubcategoryViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subcategory_item, viewGroup, false);
            viewHolder = new SubcategoryViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SubcategoryViewHolder) view.getTag();
        }
        setupSubcategoryViewHolder(categories.get(groupPosition).getSubcategories()
                .get(childPosition), viewHolder);
        return view;
    }

    private void setupSubcategoryViewHolder(Category subcategory, SubcategoryViewHolder viewHolder) {
        viewHolder.nameTv.setText(subcategory.getName());
        viewHolder.itemCb.setSelected(subcategory.isChecked());
        viewHolder.itemCb.setOnClickListener(view -> onCategoryCheckBoxClick.onNext(subcategory));
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        categories.get(groupPosition).setExpanded(false);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        categories.get(groupPosition).setExpanded(true);
    }

    public PublishSubject<Category> getOnCategoryClick() {
        return onCategoryClick;
    }

    public PublishSubject<Category> getOnCategoryCheckBoxClick() {
        return onCategoryCheckBoxClick;
    }
}
