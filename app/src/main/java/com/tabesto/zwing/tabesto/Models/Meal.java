package com.tabesto.zwing.tabesto.Models;


import java.io.Serializable;

public class Meal implements Serializable {

    private String strMeal;
    private String strCategory;
    private String strMealThumb;
    private String strArea;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String price;

    public Meal() {
    }

    public Meal(String strMeal, String strCategory, String strMealThumb, String strArea, String strIngredient1, String strIngredient2, String strIngredient3) {
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        //this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strArea = strArea;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
    }


    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "Meal='" + strMeal + '\'' +
                ", Category='" + strCategory + '\'' +
                ", Picture='" + strMealThumb + '\'' +
                //", Instructions='" + strInstructions + '\'' +
                ", Area='" + strArea + '\'' +
                ", Ingredient 1='" + strIngredient1 + '\'' +
                ", Ingredient 2='" + strIngredient2 + '\'' +
                ", Ingredient 3='" + strIngredient3 + '\'' +
                '}';
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String prix) {
        this.price = prix;
    }
}
