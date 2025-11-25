package com.example.swaggerexercise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "ユーザー作成・更新リクエスト")
public class UserRequest {

    @Schema(description = "ユーザー名", example = "山田太郎")
    @NotBlank(message = "ユーザー名は必須です")
    private String name;

    @Schema(description = "メールアドレス", example = "yamada@example.com")
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "有効なメールアドレスを入力してください")
    private String email;

    public UserRequest() {
    }

    public UserRequest(String name, String email) {
        this.name = name;
        this.email = email;
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
}
