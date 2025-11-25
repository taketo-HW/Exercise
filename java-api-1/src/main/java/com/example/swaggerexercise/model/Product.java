package com.example.swaggerexercise.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "商品情報")
public class Product {

    @Schema(description = "商品ID", example = "1")
    private Long id;

    @Schema(description = "商品名", example = "サンプル商品")
    private String name;

    @Schema(description = "商品説明", example = "これはサンプル商品です")
    private String description;

    @Schema(description = "価格", example = "1000.00")
    private Double price;

    @Schema(description = "カテゴリ", example = "電子機器")
    private String category;

    @Schema(description = "作成日時")
    private LocalDateTime createdAt;

    @Schema(description = "更新日時")
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
