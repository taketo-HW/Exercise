package com.example.swaggerexercise.controller;

import com.example.swaggerexercise.dto.ProductRequest;
import com.example.swaggerexercise.model.Product;
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
@RequestMapping("/api/products")
@Tag(name = "Products", description = "商品管理API")
public class ProductController {

    // 簡易的なインメモリストレージ（実際の実装ではデータベースを使用）
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // サンプルデータを初期化
    public ProductController() {
        Product product1 = new Product(1L, "ノートPC", "高性能なノートパソコン", 98000.0, "電子機器");
        product1.setCreatedAt(LocalDateTime.now().minusDays(10));
        product1.setUpdatedAt(LocalDateTime.now().minusDays(10));
        products.put(1L, product1);

        Product product2 = new Product(2L, "マウス", "ワイヤレスマウス", 2500.0, "電子機器");
        product2.setCreatedAt(LocalDateTime.now().minusDays(5));
        product2.setUpdatedAt(LocalDateTime.now().minusDays(5));
        products.put(2L, product2);
    }

    @GetMapping
    @Operation(summary = "商品一覧取得", description = "商品の一覧を取得します")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = Product.class)))
    public ResponseEntity<Map<String, Object>> getProducts(
            @Parameter(description = "ページ番号", example = "1") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "1ページあたりの件数", example = "10") @RequestParam(defaultValue = "10") Integer size) {
        List<Product> productList = new ArrayList<>(products.values());
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("products", productList);
        response.put("total", productList.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "商品詳細取得", description = "指定されたIDの商品情報を取得します")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "商品が見つかりません")
    })
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "商品ID", required = true, example = "1") @PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Operation(summary = "商品作成", description = "新しい商品を作成します")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "作成成功", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "リクエストが不正です")
    })
    public ResponseEntity<Product> createProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "商品作成リクエスト", required = true) @Valid @RequestBody ProductRequest request) {
        Long newId = idGenerator.getAndIncrement();
        Product product = new Product(newId, request.getName(), request.getDescription(), request.getPrice(),
                request.getCategory());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        products.put(newId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "商品更新", description = "指定されたIDの商品情報を更新します")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "更新成功", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "リクエストが不正です"),
            @ApiResponse(responseCode = "404", description = "商品が見つかりません")
    })
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "商品ID", required = true, example = "1") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "商品更新リクエスト", required = true) @Valid @RequestBody ProductRequest request) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setUpdatedAt(LocalDateTime.now());
        products.put(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "商品削除", description = "指定されたIDの商品を削除します")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "削除成功"),
            @ApiResponse(responseCode = "404", description = "商品が見つかりません")
    })
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "商品ID", required = true, example = "1") @PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        products.remove(id);
        return ResponseEntity.noContent().build();
    }
}
