package com.ktdsuniv.admin.category.biz;

import java.util.List;

import category.schema.CategoriesSchema;

public interface CategoryBiz {

	public List<CategoriesSchema> getAllCategoryList();

	public boolean addCategory(CategoriesSchema categoriesSchema);

	public boolean deleteCategory(String categoryId);

	public boolean checkExistChild(String categoryId);

	public boolean updateCategory(String categoryId, String categoryName);

}
