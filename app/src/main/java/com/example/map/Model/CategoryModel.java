package com.example.map.Model;

public class CategoryModel {
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    private String categoryName;

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    private String categoryDesc;

    public CategoryModel(String categoryName, String categoryImage, String categoryDesc) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryDesc = categoryDesc;
    }

    private String categoryImage;

}
