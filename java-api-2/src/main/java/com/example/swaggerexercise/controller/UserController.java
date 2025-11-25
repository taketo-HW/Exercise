package com.example.swaggerexercise.controller;

import com.example.swaggerexercise.dto.UserRequest;
import com.example.swaggerexercise.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "ユーザー管理API")
public class UserController {

    // 簡易的なインメモリストレージ（実際の実装ではデータベースを使用）
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // サンプルデータを初期化
    public UserController() {
        User user1 = new User(1L, "山田太郎", "yamada@example.com");
        user1.setCreatedAt(LocalDateTime.now().minusDays(10));
        user1.setUpdatedAt(LocalDateTime.now().minusDays(10));
        users.put(1L, user1);

        User user2 = new User(2L, "佐藤花子", "sato@example.com");
        user2.setCreatedAt(LocalDateTime.now().minusDays(5));
        user2.setUpdatedAt(LocalDateTime.now().minusDays(5));
        users.put(2L, user2);
    }

    @GetMapping
    @Operation(summary = "ユーザー一覧取得", description = "すべてのユーザー一覧を取得します")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = User.class)))
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(new ArrayList<>(users.values()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "ユーザー詳細取得", description = "指定されたIDのユーザー情報を取得します")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "ユーザーが見つかりません")
    })
    public ResponseEntity<User> getUserById(
            @Parameter(description = "ユーザーID", required = true, example = "1") @PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "ユーザー作成", description = "新しいユーザーを作成します")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "作成成功", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "リクエストが不正です")
    })
    public ResponseEntity<User> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ユーザー作成リクエスト", required = true) @Valid @RequestBody UserRequest request) {
        Long newId = idGenerator.getAndIncrement();
        User user = new User(newId, request.getName(), request.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        users.put(newId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ユーザー更新", description = "指定されたIDのユーザー情報を更新します")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "更新成功", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "リクエストが不正です"),
            @ApiResponse(responseCode = "404", description = "ユーザーが見つかりません")
    })
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ユーザーID", required = true, example = "1") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ユーザー更新リクエスト", required = true) @Valid @RequestBody UserRequest request) {
        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUpdatedAt(LocalDateTime.now());
        users.put(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ユーザー削除", description = "指定されたIDのユーザーを削除します")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "削除成功"),
            @ApiResponse(responseCode = "404", description = "ユーザーが見つかりません")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ユーザーID", required = true, example = "1") @PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        users.remove(id);
        return ResponseEntity.noContent().build();
    }
}
