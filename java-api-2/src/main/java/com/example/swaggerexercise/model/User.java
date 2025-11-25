package com.example.swaggerexercise.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "ユーザー情報")
public class User {

    @Schema(description = "ユーザーID", example = "1")
    private Long id;

    @Schema(description = "ユーザー名", example = "山田太郎")
    private String name;

    @Schema(description = "メールアドレス", example = "yamada@example.com")
    private String email;

    @Schema(description = "作成日時")
    private LocalDateTime createdAt;

    @Schema(description = "更新日時")
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
