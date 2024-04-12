package com.example.mcduckshoppingwebsite.Entity;

import java.time.LocalDateTime;

public class Product {

    private String product_id;
    private String category;
    private String product_name;

    private String author;
    private String description;
    private double price;
    private int stock_quantity;
    private int review_star;
    private String review_message;
    private String image_path;

    public Product(){}

    public Product(String product_id, String category, String product_name, String author, String description, double price, int stock_quantity, int review_star, String review_message, String image_path, LocalDateTime listing_date) {
        this.product_id = product_id;
        this.category = category;
        this.product_name = product_name;
        this.author = author;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.review_star = review_star;
        this.review_message = review_message;
        this.image_path = image_path;
        this.listing_date = listing_date;
    }

    private LocalDateTime listing_date;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public int getReview_star() {
        return review_star;
    }

    public void setReview_star(int review_star) {
        this.review_star = review_star;
    }

    public String getReview_message() {
        return review_message;
    }

    public void setReview_message(String review_message) {
        this.review_message = review_message;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public LocalDateTime getListing_date() {
        return listing_date;
    }

    public void setListing_date(LocalDateTime listing_date) {
        this.listing_date = listing_date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id='" + product_id + '\'' +
                ", category='" + category + '\'' +
                ", product_name='" + product_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock_quantity=" + stock_quantity +
                ", review_star=" + review_star +
                ", review_message='" + review_message + '\'' +
                ", image_path='" + image_path + '\'' +
                ", listing_date=" + listing_date +
                '}';
    }
}
