# Swagger研修課題

## 概要
この研修では、Dockerを使用してSwagger UI環境を構築し、Java API（Spring Boot）のOpenAPI仕様書を作成・管理する方法を学習します。

## 3つの環境について

この研修では、3つの異なる環境を使用します：

- **http://localhost:8080** (Docker環境) - **Swagger UI表示環境**
  - OpenAPI仕様書（YAMLファイル）を表示・確認する環境
  - 複数のYAMLファイルを選択できます：
    - `openapi/sample.yaml`（Products API - 完全版）
    - `openapi/exercise/user-api.yaml`（Users API - GETのみ）
  - 画面上部のドロップダウンから切り替え可能

- **http://localhost:8081** (プロジェクト1) - **触ってみよう**
  - **商品管理API（Products）**が実装済み
  - `openapi/sample.yaml`に**商品管理API（Products）**の完全なOpenAPI仕様書が作成済み
  - **演習内容**: Swagger UIの操作やOpenAPI仕様書の構造を理解する
  - APIも仕様書も完成している状態で、確認・操作できる見本

- **http://localhost:8082** (プロジェクト2) - **実際に書いてみよう**
  - **ユーザー管理API（Users）**が実装済み（GETのみ実装、POST/PUT/DELETEは未実装）
  - `openapi/exercise/user-api.yaml`に**ユーザー管理API（Users）**のOpenAPI仕様書を作成する課題
  - **演習内容**: 実装済みのJava APIのソースコードを確認し、それに合わせてOpenAPI仕様書を作成する
  - APIはGETのみ実装済み、仕様書もGETのみ定義済み（他のエンドポイントは課題として作成する）

## 前提条件
- Docker Desktopがインストールされていること
- Java 17以上
- Maven または Gradle

## 環境セットアップ

### 前提条件の確認

1. **Docker Desktopの起動確認**
   - Windows: タスクバーまたはスタートメニューからDocker Desktopを起動
   - Docker Desktopが起動している場合、タスクバーにDockerアイコンが表示されます

2. **Dockerの動作確認**
   ```bash
   docker --version
   docker-compose --version
   ```

### すべての環境を起動する

以下の3つの環境を起動します。起動順序は任意です。

#### 1. Docker環境（8080）の起動

```bash
docker-compose up -d
```

**アクセス先**:
- http://localhost:8080 - Swagger UI（複数のYAMLファイルを選択可能）

**停止方法**:
```bash
docker-compose down
```

#### 2. プロジェクト1（8081 - Products API）の起動

```bash
mvn -f java-api-1/pom.xml spring-boot:run
```

**アクセス先**:
- http://localhost:8081/ - Swagger UI
- http://localhost:8081/api/health - ヘルスチェック
- http://localhost:8081/api/products - 商品一覧

**内容**: 商品管理API（Products）がすべて実装済み、仕様書も完成済み（見本）

#### 3. プロジェクト2（8082 - Users API）の起動

```bash
mvn -f java-api-2/pom.xml spring-boot:run
```

**アクセス先**:
- http://localhost:8082/ - Swagger UI
- http://localhost:8082/api/health - ヘルスチェック
- http://localhost:8082/api/users - ユーザー一覧

**内容**: ユーザー管理API（Users）がすべて実装済み、仕様書はGETのみ定義済み（課題）

### 起動確認

各環境が正常に起動しているか確認：

```bash
# ポート8080（Docker）
docker-compose ps

# ポート8081（プロジェクト1）
netstat -ano | findstr :8081

# ポート8082（プロジェクト2）
netstat -ano | findstr :8082
```

### 各環境の詳細

#### Docker環境（8080）
- **用途**: YAMLファイルを表示・確認する環境
- **表示内容**: 複数のYAMLファイルを選択可能
  - Products API (完全版): `openapi/sample.yaml`
  - Users API (GETのみ): `openapi/exercise/user-api.yaml`

#### プロジェクト1（8081）
- **用途**: 触ってみよう（見本）
- **API**: 商品管理API（Products）がすべて実装済み
- **仕様書**: `openapi/sample.yaml`が完成済み
- **状態**: APIも仕様書も完成済み（見本として使用）

#### プロジェクト2（8082）
- **用途**: 実際に書いてみよう（課題）
- **API**: ユーザー管理API（Users）がすべて実装済み
- **仕様書**: `openapi/exercise/user-api.yaml`にGETのみ定義済み
- **状態**: APIは完成済み、仕様書はGETのみ（POST/PUT/DELETEの仕様書を課題として作成）

## 課題

### 課題1: 環境の起動と確認
上記の「環境セットアップ」を参考に、以下の環境を起動し、それぞれのURLにアクセスできることを確認してください：
- Docker環境: http://localhost:8080
- プロジェクト1: http://localhost:8081
- プロジェクト2: http://localhost:8082

### 課題2: 触ってみよう - サンプルAPI仕様書の確認
http://localhost:8081/ にアクセスし、Swagger UIで表示されている**商品管理API（Products）**を確認してください。
- **商品管理API（Products）**はJava APIに実装済み、OpenAPI仕様書も完成済みです
- すべてのAPIエンドポイント（GET, POST, PUT, DELETE）が定義されている見本です
- 各エンドポイントを展開して、リクエスト/レスポンスの構造を確認してください
- "Try it out"機能を使って、実際のAPIを試してみてください
- 完成したAPIと仕様書の関係を理解してください

### 課題3: 実際に書いてみよう - OpenAPI仕様書の作成

プロジェクト2を起動し、http://localhost:8082/ にアクセスしてください。
**ユーザー管理API（Users）**はすべて実装済みです（GET, POST, PUT, DELETE）。

**課題**: 実装済みのJava APIのソースコードを確認し、それに合わせて `openapi/exercise/user-api.yaml` にOpenAPI仕様書を作成してください。

現在、`user-api.yaml`にはGETエンドポイントのみ定義されています。POST, PUT, DELETEエンドポイントの仕様書を追加してください。

#### ステップ1: 参照すべきファイルを確認する

以下のファイルを開いて、内容を確認してください：

1. **Java APIの実装を確認**
   - `java-api-2/src/main/java/com/example/swaggerexercise/controller/UserController.java`
     - すべてのエンドポイント（GET, POST, PUT, DELETE）の実装を確認
     - 各エンドポイントのアノテーション（@Operation, @ApiResponsesなど）を確認
     - リクエスト/レスポンスの型を確認
   - `java-api-2/src/main/java/com/example/swaggerexercise/model/User.java`
     - Userエンティティのフィールドを確認（id, name, email, createdAt, updatedAt）
   - `java-api-2/src/main/java/com/example/swaggerexercise/dto/UserRequest.java`
     - UserRequestのフィールドを確認（name, email）
     - バリデーションアノテーション（@NotBlank, @Email）を確認

2. **見本の仕様書を確認**
   - `openapi/sample.yaml`
     - Products APIの完全な仕様書（見本）
     - POST /api/products（78-100行目）を参考にする
     - PUT /api/products/{id}（128-157行目）を参考にする
     - DELETE /api/products/{id}（159-175行目）を参考にする
     - ProductRequestスキーマ（223-245行目）を参考にする
     - Errorスキーマ（247-264行目）を参考にする
     - BadRequest, NotFound, InternalServerErrorレスポンス（266-295行目）を参考にする

3. **現在の仕様書を確認**
   - `openapi/exercise/user-api.yaml`
     - GETエンドポイントは完成済み（12-55行目）
     - TODOコメントがある箇所（57-60行目、94-99行目）に追加する

#### ステップ2: POST /api/users エンドポイントを追加する

`openapi/exercise/user-api.yaml`の`/api/users`パスに、`post`メソッドを追加してください。

**参考**: `openapi/sample.yaml`の78-100行目（POST /api/products）を参考にしてください。

**追加する内容**:
- `summary`: "ユーザー作成"
- `description`: "新しいユーザーを作成します"
- `operationId`: "createUser"
- `tags`: ["Users"]
- `requestBody`: UserRequestスキーマを参照
- `responses`:
  - `201`: 作成成功（Userスキーマを返す）
  - `400`: BadRequest（リクエストが不正）
  - `500`: InternalServerError（サーバー内部エラー）

**追加場所**: `openapi/exercise/user-api.yaml`の30行目（GET /api/usersの`responses`の後）に追加

#### ステップ3: PUT /api/users/{id} エンドポイントを追加する

`openapi/exercise/user-api.yaml`の`/api/users/{id}`パスに、`put`メソッドを追加してください。

**参考**: `openapi/sample.yaml`の128-157行目（PUT /api/products/{id}）を参考にしてください。

**追加する内容**:
- `summary`: "ユーザー更新"
- `description`: "指定されたIDのユーザー情報を更新します"
- `operationId`: "updateUser"
- `tags`: ["Users"]
- `parameters`: idパラメータ（GETと同じ）
- `requestBody`: UserRequestスキーマを参照
- `responses`:
  - `200`: 更新成功（Userスキーマを返す）
  - `400`: BadRequest（リクエストが不正）
  - `404`: NotFound（ユーザーが見つからない）
  - `500`: InternalServerError（サーバー内部エラー）

**追加場所**: `openapi/exercise/user-api.yaml`の55行目（GET /api/users/{id}の`responses`の後）に追加

#### ステップ4: DELETE /api/users/{id} エンドポイントを追加する

`openapi/exercise/user-api.yaml`の`/api/users/{id}`パスに、`delete`メソッドを追加してください。

**参考**: `openapi/sample.yaml`の159-175行目（DELETE /api/products/{id}）を参考にしてください。

**追加する内容**:
- `summary`: "ユーザー削除"
- `description`: "指定されたIDのユーザーを削除します"
- `operationId`: "deleteUser"
- `tags`: ["Users"]
- `parameters`: idパラメータ（GETと同じ）
- `responses`:
  - `204`: 削除成功（ボディなし）
  - `404`: NotFound（ユーザーが見つからない）
  - `500`: InternalServerError（サーバー内部エラー）

**追加場所**: `openapi/exercise/user-api.yaml`のPUTエンドポイントの後（ステップ3で追加した後）に追加

#### ステップ5: UserRequestスキーマを追加する

`openapi/exercise/user-api.yaml`の`components.schemas`セクションに、`UserRequest`スキーマを追加してください。

**参考**: `openapi/sample.yaml`の223-245行目（ProductRequestスキーマ）を参考にしてください。

**追加する内容**:
```yaml
UserRequest:
  type: object
  required:
    - name
    - email
  properties:
    name:
      type: string
      description: ユーザー名
      example: "山田太郎"
    email:
      type: string
      description: メールアドレス
      example: "yamada@example.com"
```

**追加場所**: `openapi/exercise/user-api.yaml`の93行目（Userスキーマの後）に追加

#### ステップ6: Errorスキーマを追加する

`openapi/exercise/user-api.yaml`の`components.schemas`セクションに、`Error`スキーマを追加してください。

**参考**: `openapi/sample.yaml`の247-264行目（Errorスキーマ）を参考にしてください。

**追加する内容**:
```yaml
Error:
  type: object
  required:
    - code
    - message
  properties:
    code:
      type: string
      description: エラーコード
      example: "ERROR_CODE"
    message:
      type: string
      description: エラーメッセージ
      example: "エラーが発生しました"
    details:
      type: string
      description: エラー詳細
      example: "詳細なエラー情報"
```

**追加場所**: `openapi/exercise/user-api.yaml`のUserRequestスキーマの後（ステップ5で追加した後）に追加

#### ステップ7: 共通レスポンスを追加する

`openapi/exercise/user-api.yaml`の`components.responses`セクションに、以下の共通レスポンスを追加してください。

**参考**: `openapi/sample.yaml`の266-295行目（BadRequest, NotFound, InternalServerError）を参考にしてください。

**追加する内容**:

1. **BadRequest**:
```yaml
BadRequest:
  description: リクエストが不正です
  content:
    application/json:
      schema:
        $ref: '#/components/schemas/Error'
      example:
        code: "BAD_REQUEST"
        message: "リクエストパラメータが不正です"
```

2. **NotFound**:
```yaml
NotFound:
  description: リソースが見つかりません
  content:
    application/json:
      schema:
        $ref: '#/components/schemas/Error'
      example:
        code: "NOT_FOUND"
        message: "指定されたリソースが見つかりません"
```

3. **InternalServerError**:
```yaml
InternalServerError:
  description: サーバー内部エラー
  content:
    application/json:
      schema:
        $ref: '#/components/schemas/Error'
      example:
        code: "INTERNAL_SERVER_ERROR"
        message: "サーバー内部でエラーが発生しました"
```

**追加場所**: `openapi/exercise/user-api.yaml`の`components.responses`セクション（99行目のTODOコメントの後）に追加

#### ステップ8: 完成した仕様書を確認する

1. Docker環境を再起動して、http://localhost:8080 にアクセス
2. ドロップダウンから「Users API (GETのみ)」を選択
3. 追加したPOST, PUT, DELETEエンドポイントが表示されることを確認
4. 各エンドポイントを展開して、リクエスト/レスポンスが正しく定義されているか確認

#### 注意事項

- YAMLのインデント（スペース2つ）に注意してください
- `$ref`でスキーマを参照する際は、`#/components/schemas/`または`#/components/responses/`のパスを正しく指定してください
- `openapi/sample.yaml`の構造をそのままコピーするのではなく、Users APIに合わせて内容を変更してください（Products → Users、Product → Userなど）

## ディレクトリ構成
```
Exercise/
├── README.md
├── docker-compose.yml
├── openapi/
│   ├── sample.yaml (Products API - 完全版)
│   ├── swagger-config.json (複数YAMLファイル選択用設定)
│   └── exercise/
│       └── user-api.yaml (Users API - GETのみ)
├── java-api-1/
│   └── (Products API - 完全版、ポート8081)
└── java-api-2/
    └── (Users API - GETのみ、ポート8082)
```
