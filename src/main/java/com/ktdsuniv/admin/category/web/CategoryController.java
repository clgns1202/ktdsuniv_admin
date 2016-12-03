package com.ktdsuniv.admin.category.web;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniv.admin.category.service.CategoryService;

import category.schema.CategoriesSchema;

@Controller
public class CategoryController {

	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping("/category/categoryPage")
	public ModelAndView viewCategoryPage() {
		List<CategoriesSchema> categories = categoryService.getAllCategoryList();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("category/categoryPage");
		view.addObject("categories", categories);
		
		return view;
	}
	
	@RequestMapping("/category/getAllCategory")
	@ResponseBody
	public List<CategoriesSchema> getAllCategoryAction() {
		List<CategoriesSchema> categories = categoryService.getAllCategoryList();
		return categories;
	}
	
	@RequestMapping("/category/doAddCategory")
	@ResponseBody
	public CategoriesSchema doAddCategoryAction(CategoriesSchema categoriesSchema) {
		categoryService.addCategory(categoriesSchema);
		return categoryService.getCategoryByName(categoriesSchema.getCategoryName());
	}

	@RequestMapping("/category/doDeleteCategory/{categoryId}")
	@ResponseBody
	public boolean doDeleteCategoryAction(@PathVariable String categoryId) {
		
		boolean isExistChild = categoryService.checkExistChild(categoryId);
		if ( !isExistChild ) {
			return categoryService.deleteCategory(categoryId);
		}
		else {
			return false;
		}
	}
	
}
