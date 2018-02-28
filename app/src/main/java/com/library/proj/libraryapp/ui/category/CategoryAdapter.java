package com.library.proj.libraryapp.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

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
        setupCategoryViewHolder(categories.get(position), (ExpandableListView) viewGroup,
                position, viewHolder);
        return view;
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

    private void setupCategoryViewHolder(Category category, ExpandableListView viewGroup,
                                         int position, CategoryViewHolder viewHolder) {
        viewHolder.nameTv.setText(category.getName());
        viewHolder.itemCb.setChecked(category.isChecked());
        setupArrowIv(category, viewGroup, position, viewHolder);
        viewHolder.itemCl.setOnClickListener(v -> onCategoryClick.onNext(category));
        viewHolder.itemCb.setOnClickListener(view -> onCategoryCheckBoxClick.onNext(category));
    }

    private void setupArrowIv(Category category, ExpandableListView viewGroup, int position, CategoryViewHolder viewHolder) {
        if(category.getSubcategories() == null || category.getSubcategories().isEmpty()) {
            viewHolder.arrowIv.setBackgroundResource(0);
        } else {
            handleExpanding(category, viewGroup, position, viewHolder);
        }
    }

    private void handleExpanding(Category category, ExpandableListView viewGroup, int position,
                                 CategoryViewHolder viewHolder) {
        if (category.isExpanded()) {
            viewHolder.arrowIv.setBackgroundResource(R.drawable.ic_arrow_up);
            viewGroup.expandGroup(position);
        } else {
            viewHolder.arrowIv.setBackgroundResource(R.drawable.ic_arrow_down);
            viewGroup.collapseGroup(position);
        }
    }

    private void setupSubcategoryViewHolder(Category subcategory, SubcategoryViewHolder viewHolder) {
        viewHolder.nameTv.setText(subcategory.getName());
        viewHolder.itemCb.setChecked(subcategory.isChecked());
        viewHolder.itemCb.setOnClickListener(view -> onCategoryCheckBoxClick.onNext(subcategory));
    }

    public PublishSubject<Category> getOnCategoryClick() {
        return onCategoryClick;
    }

    public PublishSubject<Category> getOnCategoryCheckBoxClick() {
        return onCategoryCheckBoxClick;
    }
}
