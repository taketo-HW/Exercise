package com.example.swaggerexercise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "商品作成・更新リクエスト")
public class ProductRequest {

    @Schema(description = "商品名", example = "サンプル商品")
    @NotBlank(message = "商品名は必須です")
    private String name;

    @Schema(description = "商品説明", example = "これはサンプル商品です")
    private String description;

    @Schema(description = "価格", example = "1000.00")
    @NotNull(message = "価格は必須です")
    @Positive(message = "価格は正の数である必要があります")
    private Double price;

    @Schema(description = "カテゴリ", example = "電子機器")
    private String category;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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
}
