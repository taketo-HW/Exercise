研修用の資料です。

## MySQLサーバーの立ち上げ方

### 前提条件
- Docker Desktopがインストールされていること
- Docker Composeが利用可能であること

### 起動手順

1. **コンテナの起動**
   ```bash
   docker-compose up -d
   ```

2. **起動確認**
   ```bash
   docker-compose ps
   ```
   `mysql-container`が`Up`状態になっていることを確認してください。

3. **ログの確認（必要に応じて）**
   ```bash
   docker-compose logs mysql
   ```

### MySQLへの接続

#### コマンドラインから接続
```bash
docker exec -it mysql-container mysql -uroot -proot
```

MySQLから抜ける場合は、以下のコマンドを実行してください。
```sql
exit
```
または
```sql
quit
```

#### 接続情報
- **ホスト**: `localhost`
- **ポート**: `3306`
- **ユーザー名**: `root`
- **パスワード**: `root`
- **データベース名**: `demo`

#### 外部ツールから接続する場合
以下の情報を使用してMySQLクライアント（例：MySQL Workbench、DBeaver、TablePlusなど）から接続できます。

```
ホスト: localhost
ポート: 3306
ユーザー名: root
パスワード: root
データベース: demo
```

### 停止・削除

#### コンテナの停止
```bash
docker-compose stop
```

#### コンテナの停止と削除（データは保持）
```bash
docker-compose down
```

#### コンテナの停止と削除（データも削除）
```bash
docker-compose down -v
```

### トラブルシューティング

#### ポート3306が既に使用されている場合
`docker-compose.yml`の`ports`セクションを変更してください。
```yaml
ports:
  - "3307:3306"  # ホスト側のポートを3307に変更
```

#### コンテナが起動しない場合
ログを確認してください。
```bash
docker-compose logs mysql
```