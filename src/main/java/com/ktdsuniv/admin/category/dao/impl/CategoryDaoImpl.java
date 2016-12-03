package com.ktdsuniv.admin.category.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ktdsuniv.admin.category.dao.CategoryDao;

import category.schema.CategoriesSchema;
import common.support.mongo.MongoTemplateSupport;

public class CategoryDaoImpl extends MongoTemplateSupport implements CategoryDao {

	@Override
	public List<CategoriesSchema> getAllCategoryList() {
		return getMongo().findAll(CategoriesSchema.class);
	}

	@Override
	public int addCategory(CategoriesSchema categoriesSchema) {
		getMongo().insert(categoriesSchema);
		return 1;
	}

	@Override
	public int deleteCategory(String categoryId) {
		Query query = new Query(new Criteria("id").is(categoryId));
		getMongo().remove(query, CategoriesSchema.class);
		return 1;
	}

	@Override
	public int checkExistChild(String categoryId) {
		Query query = new Query(new Criteria("parentId").is(categoryId));
		int count = (int) getMongo().count(query, CategoriesSchema.class);
		if ( count > 0 ) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
